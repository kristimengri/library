package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.AuthorToBook;
import com.kent.gmail.com.runtime.model.BookToGenre;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List Book */
@IdValid.List({
  @IdValid(
      field = "bookBookToGenresIds",
      fieldType = BookToGenre.class,
      targetField = "bookBookToGenreses"),
  @IdValid(
      field = "bookAuthorToBooksIds",
      fieldType = AuthorToBook.class,
      targetField = "bookAuthorToBookses")
})
public class BookFilter extends BaseFilter {

  private Set<String> bookAuthorToBooksIds;

  @JsonIgnore private List<AuthorToBook> bookAuthorToBookses;

  private Set<String> bookBookToGenresIds;

  @JsonIgnore private List<BookToGenre> bookBookToGenreses;

  /**
   * @return bookAuthorToBooksIds
   */
  public Set<String> getBookAuthorToBooksIds() {
    return this.bookAuthorToBooksIds;
  }

  /**
   * @param bookAuthorToBooksIds bookAuthorToBooksIds to set
   * @return BookFilter
   */
  public <T extends BookFilter> T setBookAuthorToBooksIds(Set<String> bookAuthorToBooksIds) {
    this.bookAuthorToBooksIds = bookAuthorToBooksIds;
    return (T) this;
  }

  /**
   * @return bookAuthorToBookses
   */
  @JsonIgnore
  public List<AuthorToBook> getBookAuthorToBookses() {
    return this.bookAuthorToBookses;
  }

  /**
   * @param bookAuthorToBookses bookAuthorToBookses to set
   * @return BookFilter
   */
  public <T extends BookFilter> T setBookAuthorToBookses(List<AuthorToBook> bookAuthorToBookses) {
    this.bookAuthorToBookses = bookAuthorToBookses;
    return (T) this;
  }

  /**
   * @return bookBookToGenresIds
   */
  public Set<String> getBookBookToGenresIds() {
    return this.bookBookToGenresIds;
  }

  /**
   * @param bookBookToGenresIds bookBookToGenresIds to set
   * @return BookFilter
   */
  public <T extends BookFilter> T setBookBookToGenresIds(Set<String> bookBookToGenresIds) {
    this.bookBookToGenresIds = bookBookToGenresIds;
    return (T) this;
  }

  /**
   * @return bookBookToGenreses
   */
  @JsonIgnore
  public List<BookToGenre> getBookBookToGenreses() {
    return this.bookBookToGenreses;
  }

  /**
   * @param bookBookToGenreses bookBookToGenreses to set
   * @return BookFilter
   */
  public <T extends BookFilter> T setBookBookToGenreses(List<BookToGenre> bookBookToGenreses) {
    this.bookBookToGenreses = bookBookToGenreses;
    return (T) this;
  }
}
