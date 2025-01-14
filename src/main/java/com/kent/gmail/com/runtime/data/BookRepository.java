package com.kent.gmail.com.runtime.data;

import com.kent.gmail.com.runtime.model.AuthorToBook;
import com.kent.gmail.com.runtime.model.AuthorToBook_;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.model.BookToGenre;
import com.kent.gmail.com.runtime.model.BookToGenre_;
import com.kent.gmail.com.runtime.model.Book_;
import com.kent.gmail.com.runtime.request.BookFilter;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  @Autowired private BaseRepository baseRepository;

  /**
   * @param bookFilter Object Used to List Book
   * @param securityContext
   * @return List of Book
   */
  public List<Book> listAllBooks(BookFilter bookFilter, UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Book> q = cb.createQuery(Book.class);
    Root<Book> r = q.from(Book.class);
    List<Predicate> preds = new ArrayList<>();
    addBookPredicate(bookFilter, cb, q, r, preds, securityContext);
    q.select(r).where(preds.toArray(new Predicate[0]));
    TypedQuery<Book> query = em.createQuery(q);

    if (bookFilter.getPageSize() != null
        && bookFilter.getCurrentPage() != null
        && bookFilter.getPageSize() > 0
        && bookFilter.getCurrentPage() > -1) {
      query
          .setFirstResult(bookFilter.getPageSize() * bookFilter.getCurrentPage())
          .setMaxResults(bookFilter.getPageSize());
    }

    return query.getResultList();
  }

  public <T extends Book> void addBookPredicate(
      BookFilter bookFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      UserSecurityContext securityContext) {

    baseRepository.addBasePredicate(bookFilter, cb, q, r, preds, securityContext);

    if (bookFilter.getBookBookToGenreses() != null
        && !bookFilter.getBookBookToGenreses().isEmpty()) {
      Set<String> ids =
          bookFilter.getBookBookToGenreses().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, BookToGenre> join = r.join(Book_.bookBookToGenres);
      preds.add(join.get(BookToGenre_.id).in(ids));
    }

    if (bookFilter.getBookAuthorToBookses() != null
        && !bookFilter.getBookAuthorToBookses().isEmpty()) {
      Set<String> ids =
          bookFilter.getBookAuthorToBookses().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, AuthorToBook> join = r.join(Book_.bookAuthorToBooks);
      preds.add(join.get(AuthorToBook_.id).in(ids));
    }
  }

  /**
   * @param bookFilter Object Used to List Book
   * @param securityContext
   * @return count of Book
   */
  public Long countAllBooks(BookFilter bookFilter, UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<Book> r = q.from(Book.class);
    List<Predicate> preds = new ArrayList<>();
    addBookPredicate(bookFilter, cb, q, r, preds, securityContext);
    q.select(cb.count(r)).where(preds.toArray(new Predicate[0]));
    TypedQuery<Long> query = em.createQuery(q);
    return query.getSingleResult();
  }

  public void remove(Object o) {
    em.remove(o);
  }

  public <T extends Book, I> List<T> listByIds(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      Set<I> ids,
      UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> q = cb.createQuery(c);
    Root<T> r = q.from(c);
    List<Predicate> preds = new ArrayList<>();
    preds.add(r.get(idField).in(ids));

    q.select(r).where(preds.toArray(new Predicate[0]));
    return em.createQuery(q).getResultList();
  }

  public <T extends Book, I> T getByIdOrNull(
      Class<T> c, SingularAttribute<? super T, I> idField, I id) {
    return getByIdOrNull(c, idField, id, null);
  }

  public <T extends Book, I> List<T> listByIds(
      Class<T> c, SingularAttribute<? super T, I> idField, Set<I> ids) {
    return listByIds(c, idField, ids, null);
  }

  public <T extends Book, I> T getByIdOrNull(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      I id,
      UserSecurityContext securityContext) {
    return listByIds(c, idField, Collections.singleton(id), securityContext).stream()
        .findFirst()
        .orElse(null);
  }

  @Transactional
  public <T> T merge(T base) {
    T updated = em.merge(base);
    applicationEventPublisher.publishEvent(updated);
    return updated;
  }

  @Transactional
  public void massMerge(List<?> toMerge) {
    for (Object o : toMerge) {
      java.lang.Object updated = em.merge(o);
      applicationEventPublisher.publishEvent(updated);
    }
  }
}
