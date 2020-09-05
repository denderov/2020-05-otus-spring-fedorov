package ru.otus.atheneum.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.GenreService;

//@EnableAutoConfiguration(exclude = {MongockConfiguration.class, MongockTestContext.class})
@DisplayName("Класс BookController ")
@ExtendWith(MockitoExtension.class)
public class BookControllerUnitTest {

  @Mock
  private BookService bookService;

  @Mock
  private AuthorService authorService;

  @Mock
  private GenreService genreService;

  @Mock
  private Model model;

//  @Test
//  @DisplayName("корректно вызывает удаление книги")
//  void shouldCorrectDeleteBook() {
//    BookController bookController = new BookController(bookService, authorService,
//        genreService, new EntityConverterImpl(authorService, genreService, new ModelMapper()));
//    String test_id = TestHelper.BOOK_1.getId();
//    bookController.deleteBookById(model, test_id);
//    verify(bookService, times(1))
//        .delete(test_id);
//  }
}