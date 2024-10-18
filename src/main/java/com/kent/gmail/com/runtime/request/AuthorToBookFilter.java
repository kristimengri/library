package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.Author;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List AuthorToBook */
@IdValid.List({
  @IdValid(field = "authorIds", fieldType = Author.class, targetField = "authors"),
  @IdValid(field = "bookIds", fieldType = Book.class, targetField = "books")
})
public class AuthorToBookFilter extends BaseFilter {

  private Set<String> authorIds;

  @JsonIgnore private List<Author> authors;

  private Set<String> bookIds;

  @JsonIgnore private List<Book> books;

  private Set<Boolean> mainAuthor;

  /**
   * @return authorIds
   */
  public Set<String> getAuthorIds() {
    return this.authorIds;
  }

  /**
   * @param authorIds authorIds to set
   * @return AuthorToBookFilter
   */
  public <T extends AuthorToBookFilter> T setAuthorIds(Set<String> authorIds) {
    this.authorIds = authorIds;
    return (T) this;
  }

  /**
   * @return authors
   */
  @JsonIgnore
  public List<Author> getAuthors() {
    return this.authors;
  }

  /**
   * @param authors authors to set
   * @return AuthorToBookFilter
   */
  public <T extends AuthorToBookFilter> T setAuthors(List<Author> authors) {
    this.authors = authors;
    return (T) this;
  }

  /**
   * @return bookIds
   */
  public Set<String> getBookIds() {
    return this.bookIds;
  }

  /**
   * @param bookIds bookIds to set
   * @return AuthorToBookFilter
   */
  public <T extends AuthorToBookFilter> T setBookIds(Set<String> bookIds) {
    this.bookIds = bookIds;
    return (T) this;
  }

  /**
   * @return books
   */
  @JsonIgnore
  public List<Book> getBooks() {
    return this.books;
  }

  /**
   * @param books books to set
   * @return AuthorToBookFilter
   */
  public <T extends AuthorToBookFilter> T setBooks(List<Book> books) {
    this.books = books;
    return (T) this;
  }

  /**
   * @return mainAuthor
   */
  public Set<Boolean> getMainAuthor() {
    return this.mainAuthor;
  }

  /**
   * @param mainAuthor mainAuthor to set
   * @return AuthorToBookFilter
   */
  public <T extends AuthorToBookFilter> T setMainAuthor(Set<Boolean> mainAuthor) {
    this.mainAuthor = mainAuthor;
    return (T) this;
  }
}
