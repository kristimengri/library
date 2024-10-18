package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.Genre;
import com.kent.gmail.com.runtime.request.GenreCreate;
import com.kent.gmail.com.runtime.request.GenreFilter;
import com.kent.gmail.com.runtime.request.GenreUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.GenreService;
import com.kent.gmail.com.runtime.validation.Create;
import com.kent.gmail.com.runtime.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("Genre")
@Tag(name = "Genre")
public class GenreController {

  @Autowired private GenreService genreService;

  @PostMapping("createGenre")
  @Operation(summary = "createGenre", description = "Creates Genre")
  public Genre createGenre(
      @Validated(Create.class) @RequestBody GenreCreate genreCreate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return genreService.createGenre(genreCreate, securityContext);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "deleteGenre", description = "Deletes Genre")
  public Genre deleteGenre(@PathVariable("id") String id, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return genreService.deleteGenre(id, securityContext);
  }

  @PostMapping("getAllGenres")
  @Operation(summary = "getAllGenres", description = "lists Genres")
  public PaginationResponse<Genre> getAllGenres(
      @Valid @RequestBody GenreFilter genreFilter, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return genreService.getAllGenres(genreFilter, securityContext);
  }

  @PutMapping("updateGenre")
  @Operation(summary = "updateGenre", description = "Updates Genre")
  public Genre updateGenre(
      @Validated(Update.class) @RequestBody GenreUpdate genreUpdate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return genreService.updateGenre(genreUpdate, securityContext);
  }
}
