package com.kent.gmail.com.runtime.data;

import com.kent.gmail.com.runtime.model.BookInstance;
import com.kent.gmail.com.runtime.model.BookInstance_;
import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.model.LenderToBookInstance_;
import com.kent.gmail.com.runtime.model.Lender_;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceFilter;
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
public class LenderToBookInstanceRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  @Autowired private BaseRepository baseRepository;

  /**
   * @param lenderToBookInstanceFilter Object Used to List LenderToBookInstance
   * @param securityContext
   * @return List of LenderToBookInstance
   */
  public List<LenderToBookInstance> listAllLenderToBookInstances(
      LenderToBookInstanceFilter lenderToBookInstanceFilter, UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<LenderToBookInstance> q = cb.createQuery(LenderToBookInstance.class);
    Root<LenderToBookInstance> r = q.from(LenderToBookInstance.class);
    List<Predicate> preds = new ArrayList<>();
    addLenderToBookInstancePredicate(lenderToBookInstanceFilter, cb, q, r, preds, securityContext);
    q.select(r).where(preds.toArray(new Predicate[0]));
    TypedQuery<LenderToBookInstance> query = em.createQuery(q);

    if (lenderToBookInstanceFilter.getPageSize() != null
        && lenderToBookInstanceFilter.getCurrentPage() != null
        && lenderToBookInstanceFilter.getPageSize() > 0
        && lenderToBookInstanceFilter.getCurrentPage() > -1) {
      query
          .setFirstResult(
              lenderToBookInstanceFilter.getPageSize()
                  * lenderToBookInstanceFilter.getCurrentPage())
          .setMaxResults(lenderToBookInstanceFilter.getPageSize());
    }

    return query.getResultList();
  }

  public <T extends LenderToBookInstance> void addLenderToBookInstancePredicate(
      LenderToBookInstanceFilter lenderToBookInstanceFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      UserSecurityContext securityContext) {

    baseRepository.addBasePredicate(lenderToBookInstanceFilter, cb, q, r, preds, securityContext);

    if (lenderToBookInstanceFilter.getLenders() != null
        && !lenderToBookInstanceFilter.getLenders().isEmpty()) {
      Set<String> ids =
          lenderToBookInstanceFilter.getLenders().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, Lender> join = r.join(LenderToBookInstance_.lender);
      preds.add(join.get(Lender_.id).in(ids));
    }

    if (lenderToBookInstanceFilter.getReturnedStart() != null) {
      preds.add(
          cb.greaterThanOrEqualTo(
              r.get(LenderToBookInstance_.returned),
              lenderToBookInstanceFilter.getReturnedStart()));
    }
    if (lenderToBookInstanceFilter.getReturnedEnd() != null) {
      preds.add(
          cb.lessThanOrEqualTo(
              r.get(LenderToBookInstance_.returned), lenderToBookInstanceFilter.getReturnedEnd()));
    }

    if (lenderToBookInstanceFilter.getDueBackStart() != null) {
      preds.add(
          cb.greaterThanOrEqualTo(
              r.get(LenderToBookInstance_.dueBack), lenderToBookInstanceFilter.getDueBackStart()));
    }
    if (lenderToBookInstanceFilter.getDueBackEnd() != null) {
      preds.add(
          cb.lessThanOrEqualTo(
              r.get(LenderToBookInstance_.dueBack), lenderToBookInstanceFilter.getDueBackEnd()));
    }

    if (lenderToBookInstanceFilter.getLentStart() != null) {
      preds.add(
          cb.greaterThanOrEqualTo(
              r.get(LenderToBookInstance_.lent), lenderToBookInstanceFilter.getLentStart()));
    }
    if (lenderToBookInstanceFilter.getLentEnd() != null) {
      preds.add(
          cb.lessThanOrEqualTo(
              r.get(LenderToBookInstance_.lent), lenderToBookInstanceFilter.getLentEnd()));
    }

    if (lenderToBookInstanceFilter.getBookInstances() != null
        && !lenderToBookInstanceFilter.getBookInstances().isEmpty()) {
      Set<String> ids =
          lenderToBookInstanceFilter.getBookInstances().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, BookInstance> join = r.join(LenderToBookInstance_.bookInstance);
      preds.add(join.get(BookInstance_.id).in(ids));
    }
  }

  /**
   * @param lenderToBookInstanceFilter Object Used to List LenderToBookInstance
   * @param securityContext
   * @return count of LenderToBookInstance
   */
  public Long countAllLenderToBookInstances(
      LenderToBookInstanceFilter lenderToBookInstanceFilter, UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<LenderToBookInstance> r = q.from(LenderToBookInstance.class);
    List<Predicate> preds = new ArrayList<>();
    addLenderToBookInstancePredicate(lenderToBookInstanceFilter, cb, q, r, preds, securityContext);
    q.select(cb.count(r)).where(preds.toArray(new Predicate[0]));
    TypedQuery<Long> query = em.createQuery(q);
    return query.getSingleResult();
  }

  public void remove(Object o) {
    em.remove(o);
  }

  public <T extends LenderToBookInstance, I> List<T> listByIds(
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

  public <T extends LenderToBookInstance, I> T getByIdOrNull(
      Class<T> c, SingularAttribute<? super T, I> idField, I id) {
    return getByIdOrNull(c, idField, id, null);
  }

  public <T extends LenderToBookInstance, I> List<T> listByIds(
      Class<T> c, SingularAttribute<? super T, I> idField, Set<I> ids) {
    return listByIds(c, idField, ids, null);
  }

  public <T extends LenderToBookInstance, I> T getByIdOrNull(
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
