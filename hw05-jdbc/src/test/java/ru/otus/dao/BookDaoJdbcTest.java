package ru.otus.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.TestHelper;
import ru.otus.domain.Book;

@DisplayName("Класс BookDaoJdbc")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookDaoJdbcTest {

  @Autowired
  BookDao bookDao;

  @DisplayName("возвращает указанную книгу по id")
  @Test
  void shouldReturnBookById() {
    Book book = bookDao.getById(TestHelper.BOOK_ID_1).orElse(null);
    assertThat(book).isEqualTo(TestHelper.BOOK_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Book> book = bookDao.getById(20200626L);
    assertThat(book).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает полный список книг")
  @Test
  void shouldReturnAllBooks() {
    List<Book> books = bookDao.getAll();
    assertThat(books).isEqualTo(TestHelper.BOOKS);
  }

  @DisplayName("добавляет книгу")
  @Test
  void shouldInsertBook() {
    Book testBook = new Book(202006261730L, "Test_book", TestHelper.AUTHOR_1, TestHelper.GENRE_2);
    bookDao.insert(testBook);
    Book actualBook = bookDao.getById(testBook.getId()).orElse(null);
    System.out.println(bookDao.getAll());
    assertThat(actualBook).isEqualTo(testBook);
  }

  @DisplayName("Удаляет книгу по id и только ее")
  @Test
  void shouldDeleteBook() {
    bookDao.delete(TestHelper.BOOK_ID_1);
    List<Book> books = bookDao.getAll();
    assertThat(books).containsExactly(TestHelper.BOOK_2);
  }
}
