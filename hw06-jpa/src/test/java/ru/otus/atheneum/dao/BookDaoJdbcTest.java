package ru.otus.atheneum.dao;

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
    String test_book = "Test_book";
    Book actualBook = bookDao.insert(test_book, TestHelper.AUTHOR_1, TestHelper.GENRE_2)
        .orElseThrow();
    assertThat(actualBook).hasFieldOrPropertyWithValue("title", test_book)
        .hasFieldOrPropertyWithValue("author", TestHelper.AUTHOR_1)
        .hasFieldOrPropertyWithValue("genre", TestHelper.GENRE_2);
  }

  @DisplayName("удаляет книгу и только ее")
  @Test
  void shouldDeleteBook() {
    bookDao.delete(TestHelper.BOOK_1);
    List<Book> books = bookDao.getAll();
    assertThat(books).containsExactly(TestHelper.BOOK_2);
  }

  @DisplayName("изменяет книгу")
  @Test
  void shouldUpdateBook() {
    Book bookFromDb = TestHelper.BOOK_1;
    Book bookForUpdate =
        new Book(bookFromDb.getId(), bookFromDb.getTitle(), bookFromDb.getAuthor(),
            bookFromDb.getGenre());
    bookForUpdate.setTitle(TestHelper.BOOK_TITLE_3);
    bookForUpdate.setAuthor(TestHelper.AUTHOR_3);
    bookForUpdate.setGenre(TestHelper.GENRE_3);
    bookDao.update(bookForUpdate);
    Book actualBook = bookDao.getById(TestHelper.BOOK_ID_1).orElseThrow();
    assertThat(actualBook).hasFieldOrPropertyWithValue("title", TestHelper.BOOK_TITLE_3)
        .hasFieldOrPropertyWithValue("author", TestHelper.AUTHOR_3)
        .hasFieldOrPropertyWithValue("genre", TestHelper.GENRE_3);
  }
}
