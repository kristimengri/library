package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.AppInit;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.model.BookToGenre;
import com.kent.gmail.com.runtime.model.Genre;
import com.kent.gmail.com.runtime.request.BookToGenreCreate;
import com.kent.gmail.com.runtime.request.BookToGenreFilter;
import com.kent.gmail.com.runtime.request.BookToGenreUpdate;
import com.kent.gmail.com.runtime.request.LoginRequest;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppInit.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class BookToGenreControllerTest {

  private BookToGenre testBookToGenre;
  @Autowired private TestRestTemplate restTemplate;

  @Autowired private Book book;

  @Autowired private Genre genre;

  @BeforeAll
  private void init() {
    ResponseEntity<Object> authenticationResponse =
        this.restTemplate.postForEntity(
            "/login",
            new LoginRequest().setUsername("admin@flexicore.com").setPassword("admin"),
            Object.class);
    String authenticationKey =
        authenticationResponse.getHeaders().get(HttpHeaders.AUTHORIZATION).stream()
            .findFirst()
            .orElse(null);
    restTemplate
        .getRestTemplate()
        .setInterceptors(
            Collections.singletonList(
                (request, body, execution) -> {
                  request.getHeaders().add("Authorization", "Bearer " + authenticationKey);
                  return execution.execute(request, body);
                }));
  }

  @Test
  @Order(1)
  public void testBookToGenreCreate() {
    BookToGenreCreate request = new BookToGenreCreate();

    request.setGenreId(this.genre.getId());

    request.setBookId(this.book.getId());

    ResponseEntity<BookToGenre> response =
        this.restTemplate.postForEntity(
            "/BookToGenre/createBookToGenre", request, BookToGenre.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testBookToGenre = response.getBody();
    assertBookToGenre(request, testBookToGenre);
  }

  @Test
  @Order(2)
  public void testListAllBookToGenres() {
    BookToGenreFilter request = new BookToGenreFilter();
    ParameterizedTypeReference<PaginationResponse<BookToGenre>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<BookToGenre>> response =
        this.restTemplate.exchange(
            "/BookToGenre/getAllBookToGenres", HttpMethod.POST, new HttpEntity<>(request), t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<BookToGenre> body = response.getBody();
    Assertions.assertNotNull(body);
    List<BookToGenre> BookToGenres = body.getList();
    Assertions.assertNotEquals(0, BookToGenres.size());
    Assertions.assertTrue(
        BookToGenres.stream().anyMatch(f -> f.getId().equals(testBookToGenre.getId())));
  }

  public void assertBookToGenre(BookToGenreCreate request, BookToGenre testBookToGenre) {
    Assertions.assertNotNull(testBookToGenre);

    if (request.getGenreId() != null) {

      Assertions.assertNotNull(testBookToGenre.getGenre());
      Assertions.assertEquals(request.getGenreId(), testBookToGenre.getGenre().getId());
    }

    if (request.getBookId() != null) {

      Assertions.assertNotNull(testBookToGenre.getBook());
      Assertions.assertEquals(request.getBookId(), testBookToGenre.getBook().getId());
    }
  }

  @Test
  @Order(3)
  public void testBookToGenreUpdate() {
    BookToGenreUpdate request = new BookToGenreUpdate().setId(testBookToGenre.getId());
    ResponseEntity<BookToGenre> response =
        this.restTemplate.exchange(
            "/BookToGenre/updateBookToGenre",
            HttpMethod.PUT,
            new HttpEntity<>(request),
            BookToGenre.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testBookToGenre = response.getBody();
    assertBookToGenre(request, testBookToGenre);
  }
}
