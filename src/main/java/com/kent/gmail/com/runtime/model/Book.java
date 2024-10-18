package com.kent.gmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Book extends Base {

  @OneToMany(targetEntity = AuthorToBook.class, mappedBy = "book")
  @JsonIgnore
  private List<AuthorToBook> bookAuthorToBooks;

  @OneToMany(targetEntity = BookToGenre.class, mappedBy = "book")
  @JsonIgnore
  private List<BookToGenre> bookBookToGenres;

  /**
   * @return bookAuthorToBooks
   */
  @OneToMany(targetEntity = AuthorToBook.class, mappedBy = "book")
  @JsonIgnore
  public List<AuthorToBook> getBookAuthorToBooks() {
    return this.bookAuthorToBooks;
  }

  /**
   * @param bookAuthorToBooks bookAuthorToBooks to set
   * @return Book
   */
  public <T extends Book> T setBookAuthorToBooks(List<AuthorToBook> bookAuthorToBooks) {
    this.bookAuthorToBooks = bookAuthorToBooks;
    return (T) this;
  }

  /**
   * @return bookBookToGenres
   */
  @OneToMany(targetEntity = BookToGenre.class, mappedBy = "book")
  @JsonIgnore
  public List<BookToGenre> getBookBookToGenres() {
    return this.bookBookToGenres;
  }

  /**
   * @param bookBookToGenres bookBookToGenres to set
   * @return Book
   */
  public <T extends Book> T setBookBookToGenres(List<BookToGenre> bookBookToGenres) {
    this.bookBookToGenres = bookBookToGenres;
    return (T) this;
  }
}
