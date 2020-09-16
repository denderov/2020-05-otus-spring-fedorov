package ru.otus.atheneum.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.atheneum.service.GenreService;

@AutoConfigureDataMongo
@RunWith(SpringRunner.class)
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

  @Test
  @DisplayName("корректно отражает список книг")
  void shouldCorrectShowBooks() throws Exception {
    given(bookService.getAll()).willReturn(TestHelper.BOOKS);
    this.mvc.perform(get("/api/books"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Test_book_1")))
        .andExpect(content().string(containsString("Test_author_1")))
        .andExpect(content().string(containsString("Test_genre_1")))
        .andExpect(content().string(containsString("Test_book_2")))
        .andExpect(content().string(containsString("Test_author_2")))
        .andExpect(content().string(containsString("Test_genre_2")));
  }
}