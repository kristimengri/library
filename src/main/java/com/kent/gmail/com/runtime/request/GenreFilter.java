package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.BookToGenre;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List Genre */
@IdValid.List({
  @IdValid(
      field = "genreBookToGenresIds",
      fieldType = BookToGenre.class,
      targetField = "genreBookToGenreses")
})
public class GenreFilter extends BaseFilter {

  private Set<String> genreBookToGenresIds;

  @JsonIgnore private List<BookToGenre> genreBookToGenreses;

  /**
   * @return genreBookToGenresIds
   */
  public Set<String> getGenreBookToGenresIds() {
    return this.genreBookToGenresIds;
  }

  /**
   * @param genreBookToGenresIds genreBookToGenresIds to set
   * @return GenreFilter
   */
  public <T extends GenreFilter> T setGenreBookToGenresIds(Set<String> genreBookToGenresIds) {
    this.genreBookToGenresIds = genreBookToGenresIds;
    return (T) this;
  }

  /**
   * @return genreBookToGenreses
   */
  @JsonIgnore
  public List<BookToGenre> getGenreBookToGenreses() {
    return this.genreBookToGenreses;
  }

  /**
   * @param genreBookToGenreses genreBookToGenreses to set
   * @return GenreFilter
   */
  public <T extends GenreFilter> T setGenreBookToGenreses(List<BookToGenre> genreBookToGenreses) {
    this.genreBookToGenreses = genreBookToGenreses;
    return (T) this;
  }
}
