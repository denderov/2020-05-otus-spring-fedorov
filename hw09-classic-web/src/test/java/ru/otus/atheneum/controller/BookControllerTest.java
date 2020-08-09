package ru.otus.atheneum.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Класс BookController ")
public class BookControllerTest {

  @Autowired
  private MockMvc mvc;

  //наверное, нужно юнит тесты класть отдельно, но чтобы код не раздувать, оставим здесь
  //заодно проверим, как такие тесты вместе уживаются.
  @Autowired
  private BookController bookController;

  @MockBean
  private BookService bookService;

  @MockBean
  private Model model;

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

  @Test
  @DisplayName("корректно вызывает удаление книги")
  void shouldCorrectDeleteBook() {
    String test_id = TestHelper.BOOK_1.getId();
    bookController.deleteBook(model, test_id);
    verify(bookService, times(1))
        .delete(test_id);
  }
}