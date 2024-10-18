package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.model.Genre;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List BookToGenre */
@IdValid.List({
  @IdValid(field = "bookIds", fieldType = Book.class, targetField = "books"),
  @IdValid(field = "genreIds", fieldType = Genre.class, targetField = "genres")
})
public class BookToGenreFilter extends BaseFilter {

  private Set<String> bookIds;

  @JsonIgnore private List<Book> books;

  private Set<String> genreIds;

  @JsonIgnore private List<Genre> genres;

  /**
   * @return bookIds
   */
  public Set<String> getBookIds() {
    return this.bookIds;
  }

  /**
   * @param bookIds bookIds to set
   * @return BookToGenreFilter
   */
  public <T extends BookToGenreFilter> T setBookIds(Set<String> bookIds) {
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
   * @return BookToGenreFilter
   */
  public <T extends BookToGenreFilter> T setBooks(List<Book> books) {
    this.books = books;
    return (T) this;
  }

  /**
   * @return genreIds
   */
  public Set<String> getGenreIds() {
    return this.genreIds;
  }

  /**
   * @param genreIds genreIds to set
   * @return BookToGenreFilter
   */
  public <T extends BookToGenreFilter> T setGenreIds(Set<String> genreIds) {
    this.genreIds = genreIds;
    return (T) this;
  }

  /**
   * @return genres
   */
  @JsonIgnore
  public List<Genre> getGenres() {
    return this.genres;
  }

  /**
   * @param genres genres to set
   * @return BookToGenreFilter
   */
  public <T extends BookToGenreFilter> T setGenres(List<Genre> genres) {
    this.genres = genres;
    return (T) this;
  }
}
