package ru.otus.atheneum.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.atheneum.service.EntityConverterImpl;
import ru.otus.atheneum.service.GenreService;
import ru.otus.atheneum.service.MongoUserService;
import ru.otus.config.SecurityConfig;
import ru.otus.config.WebConfig;

@WebMvcTest(value = BookController.class)
@AutoConfigureDataMongo
@Import({EntityConverterImpl.class, WebConfig.class, SecurityConfig.class, MongoUserService.class})
@DisplayName("Класс BookController ")
public class BookControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private BookService bookService;

  @MockBean
  private AuthorService authorService;

  @MockBean
  private GenreService genreService;

  @Autowired
  private EntityConverter entityConverter;

  @Autowired
  private MongoUserService mongoUserService;

  @Test
  @DisplayName("корректно отражает список книг")
  void shouldCorrectShowBooks() throws Exception {
    given(bookService.getAll()).willReturn(TestHelper.BOOKS);
    this.mvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Test_book_1")))
        .andExpect(content().string(containsString("Test_author_1")))
        .andExpect(content().string(containsString("Test_genre_1")))
        .andExpect(content().string(containsString("Test_book_2")))
        .andExpect(content().string(containsString("Test_author_2")))
        .andExpect(content().string(containsString("Test_genre_2")));
  }

  @DisplayName("разрешает редактирование для авторизованных")
  @WithMockUser(authorities = {"USER", "ADMIN"})
  @Test
  void allowsEditForAdmin() throws Exception {
    given(genreService.getAll()).willReturn(TestHelper.GENRES);
    given(authorService.getAll()).willReturn(TestHelper.AUTHORS);
    mvc.perform(get("/book/edit")).andExpect(status().isOk());
  }

  @DisplayName("запрещает редактирование для анонимов")
  @WithAnonymousUser
  @Test
  void disableEditForAdmin() throws Exception {
    given(genreService.getAll()).willReturn(TestHelper.GENRES);
    given(authorService.getAll()).willReturn(TestHelper.AUTHORS);
    mvc.perform(get("/book/edit")).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrlPattern("**/login"))
    ;
  }
}