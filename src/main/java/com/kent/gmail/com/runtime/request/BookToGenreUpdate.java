package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.BookToGenre;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update BookToGenre */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = BookToGenre.class,
      targetField = "bookToGenre",
      groups = {Update.class})
})
public class BookToGenreUpdate extends BookToGenreCreate {

  @JsonIgnore private BookToGenre bookToGenre;

  private String id;

  /**
   * @return bookToGenre
   */
  @JsonIgnore
  public BookToGenre getBookToGenre() {
    return this.bookToGenre;
  }

  /**
   * @param bookToGenre bookToGenre to set
   * @return BookToGenreUpdate
   */
  public <T extends BookToGenreUpdate> T setBookToGenre(BookToGenre bookToGenre) {
    this.bookToGenre = bookToGenre;
    return (T) this;
  }

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return BookToGenreUpdate
   */
  public <T extends BookToGenreUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
