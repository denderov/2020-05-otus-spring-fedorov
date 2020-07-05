package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.BookDao;
import ru.otus.domain.Book;

@SpringBootTest
@DisplayName("Класс BookServiceImpl ")
public class BookServiceImplTest {

  @MockBean
  private BookDao bookDao;

  @Autowired
  private BookService bookService;

  @DisplayName("возвращает список книг из базы")
  @Test
  void shouldReturnAllBooks() {
    when(bookDao.getAll()).thenReturn(TestHelper.BOOKS);
    List<Book> books = bookService.getAll();
    assertThat(books).isEqualTo(TestHelper.BOOKS);
  }

//  @DisplayName("сохраняет книгу")
//  @Test
//  void shouldSaveBook() {
//    Book book = new Book();
//    bookService.save(TestHelper.BOOK_3);
//  }

}
