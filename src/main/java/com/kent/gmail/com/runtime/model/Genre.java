package com.kent.gmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Genre extends Base {

  @OneToMany(targetEntity = BookToGenre.class, mappedBy = "genre")
  @JsonIgnore
  private List<BookToGenre> genreBookToGenres;

  /**
   * @return genreBookToGenres
   */
  @OneToMany(targetEntity = BookToGenre.class, mappedBy = "genre")
  @JsonIgnore
  public List<BookToGenre> getGenreBookToGenres() {
    return this.genreBookToGenres;
  }

  /**
   * @param genreBookToGenres genreBookToGenres to set
   * @return Genre
   */
  public <T extends Genre> T setGenreBookToGenres(List<BookToGenre> genreBookToGenres) {
    this.genreBookToGenres = genreBookToGenres;
    return (T) this;
  }
}
