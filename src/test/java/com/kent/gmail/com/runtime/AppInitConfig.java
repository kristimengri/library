package com.kent.gmail.com.runtime;

import com.kent.gmail.com.runtime.model.Author;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.model.BookInstance;
import com.kent.gmail.com.runtime.model.Genre;
import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.request.AppUserCreate;
import com.kent.gmail.com.runtime.request.AuthorCreate;
import com.kent.gmail.com.runtime.request.BookCreate;
import com.kent.gmail.com.runtime.request.BookInstanceCreate;
import com.kent.gmail.com.runtime.request.GenreCreate;
import com.kent.gmail.com.runtime.request.LenderCreate;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.AppUserService;
import com.kent.gmail.com.runtime.service.AuthorService;
import com.kent.gmail.com.runtime.service.BookInstanceService;
import com.kent.gmail.com.runtime.service.BookService;
import com.kent.gmail.com.runtime.service.GenreService;
import com.kent.gmail.com.runtime.service.LenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitConfig {

  @Autowired private GenreService genreService;

  @Autowired private AuthorService authorService;

  @Autowired private LenderService lenderService;

  @Autowired private BookInstanceService bookInstanceService;

  @Autowired private BookService bookService;

  @Autowired
  @Qualifier("adminSecurityContext")
  private UserSecurityContext securityContext;

  @Bean
  public Genre genre() {
    GenreCreate genreCreate = new GenreCreate();
    return genreService.createGenre(genreCreate, securityContext);
  }

  @Bean
  public Author author() {
    AuthorCreate authorCreate = new AuthorCreate();
    return authorService.createAuthor(authorCreate, securityContext);
  }

  @Bean
  public Lender lender() {
    LenderCreate lenderCreate = new LenderCreate();
    return lenderService.createLender(lenderCreate, securityContext);
  }

  @Bean
  public BookInstance bookInstance() {
    BookInstanceCreate bookInstanceCreate = new BookInstanceCreate();
    return bookInstanceService.createBookInstance(bookInstanceCreate, securityContext);
  }

  @Bean
  public Book book() {
    BookCreate bookCreate = new BookCreate();
    return bookService.createBook(bookCreate, securityContext);
  }

  @Configuration
  public static class UserConfig {
    @Bean
    @Qualifier("adminSecurityContext")
    public UserSecurityContext adminSecurityContext(AppUserService appUserService) {
      com.kent.gmail.com.runtime.model.AppUser admin =
          appUserService.createAppUser(
              new AppUserCreate().setUsername("admin@flexicore.com").setPassword("admin"), null);
      return new UserSecurityContext(admin);
    }
  }
}
