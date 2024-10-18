package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class BookToGenre extends Base {

  @ManyToOne(targetEntity = Book.class)
  private Book book;

  @ManyToOne(targetEntity = Genre.class)
  private Genre genre;

  /**
   * @return book
   */
  @ManyToOne(targetEntity = Book.class)
  public Book getBook() {
    return this.book;
  }

  /**
   * @param book book to set
   * @return BookToGenre
   */
  public <T extends BookToGenre> T setBook(Book book) {
    this.book = book;
    return (T) this;
  }

  /**
   * @return genre
   */
  @ManyToOne(targetEntity = Genre.class)
  public Genre getGenre() {
    return this.genre;
  }

  /**
   * @param genre genre to set
   * @return BookToGenre
   */
  public <T extends BookToGenre> T setGenre(Genre genre) {
    this.genre = genre;
    return (T) this;
  }
}
