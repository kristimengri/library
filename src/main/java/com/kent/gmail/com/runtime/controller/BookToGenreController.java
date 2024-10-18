package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.BookToGenre;
import com.kent.gmail.com.runtime.request.BookToGenreCreate;
import com.kent.gmail.com.runtime.request.BookToGenreFilter;
import com.kent.gmail.com.runtime.request.BookToGenreUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.BookToGenreService;
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
@RequestMapping("BookToGenre")
@Tag(name = "BookToGenre")
public class BookToGenreController {

  @Autowired private BookToGenreService bookToGenreService;

  @PostMapping("createBookToGenre")
  @Operation(summary = "createBookToGenre", description = "Creates BookToGenre")
  public BookToGenre createBookToGenre(
      @Validated(Create.class) @RequestBody BookToGenreCreate bookToGenreCreate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookToGenreService.createBookToGenre(bookToGenreCreate, securityContext);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "deleteBookToGenre", description = "Deletes BookToGenre")
  public BookToGenre deleteBookToGenre(
      @PathVariable("id") String id, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookToGenreService.deleteBookToGenre(id, securityContext);
  }

  @PostMapping("getAllBookToGenres")
  @Operation(summary = "getAllBookToGenres", description = "lists BookToGenres")
  public PaginationResponse<BookToGenre> getAllBookToGenres(
      @Valid @RequestBody BookToGenreFilter bookToGenreFilter, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookToGenreService.getAllBookToGenres(bookToGenreFilter, securityContext);
  }

  @PutMapping("updateBookToGenre")
  @Operation(summary = "updateBookToGenre", description = "Updates BookToGenre")
  public BookToGenre updateBookToGenre(
      @Validated(Update.class) @RequestBody BookToGenreUpdate bookToGenreUpdate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookToGenreService.updateBookToGenre(bookToGenreUpdate, securityContext);
  }
}
