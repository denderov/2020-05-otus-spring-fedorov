package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

  @DisplayName("готовит список всех книг из базы")
  @Test
  void shouldReturnAllBooks() {
    when(bookDao.getAll()).thenReturn(TestHelper.BOOKS);
    bookService.prepareAll();
    List<Book> books = bookService.getPreparedBookList();
    assertThat(books).isEqualTo(TestHelper.BOOKS);
  }

  @DisplayName("вызывает DAO для сохранения")
  @Test
  void shouldSaveBook() {
    bookService.save(TestHelper.BOOK_TITLE_3, TestHelper.AUTHOR_3, TestHelper.GENRE_3);
    verify(bookDao, times(1))
        .insert(TestHelper.BOOK_TITLE_3, TestHelper.AUTHOR_3, TestHelper.GENRE_3);
  }

  @DisplayName("пишет книгу в DAO используя методы билдера")
  @Test
  void shouldCreateBook() {
    bookService.initBook().setTitle(TestHelper.BOOK_TITLE_3).setAuthor(TestHelper.AUTHOR_3)
        .setGenre(TestHelper.GENRE_3).createBook();
    verify(bookDao).insert(TestHelper.BOOK_TITLE_3, TestHelper.AUTHOR_3, TestHelper.GENRE_3);
  }

}
