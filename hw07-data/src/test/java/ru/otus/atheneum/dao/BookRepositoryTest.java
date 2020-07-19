package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.TestHelper;
import ru.otus.domain.Book;

@DisplayName("Класс BookRepository")
@DataJpaTest
public class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  @DisplayName("возвращает указанную книгу по id")
  @Test
  void shouldReturnBookById() {
    Book book = bookRepository.findById(TestHelper.BOOK_ID_1).orElse(null);

    assertThat(book).isEqualTo(TestHelper.BOOK_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Book> book = bookRepository.findById(20200626L);
    assertThat(book).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает полный список книг")
  @Test
  void shouldReturnAllBooks() {
    Iterable<Book> books = bookRepository.findAll();
    assertThat(books).isEqualTo(TestHelper.BOOKS);
  }

  @DisplayName("добавляет книгу")
  @Test
  void shouldInsertBook() {
    Book bookForSave = new Book();
    String test_book = "Test_book";
    bookForSave.setTitle(test_book);
    bookForSave.setAuthor(TestHelper.AUTHOR_1);
    bookForSave.setGenre(TestHelper.GENRE_2);
    Book actualBook = bookRepository.save(bookForSave);
    assertThat(actualBook).hasFieldOrPropertyWithValue("title", test_book)
        .hasFieldOrPropertyWithValue("author", TestHelper.AUTHOR_1)
        .hasFieldOrPropertyWithValue("genre", TestHelper.GENRE_2);
  }

  @DisplayName("удаляет книгу и только ее")
  @Test
  void shouldDeleteBook() {
    bookRepository.delete(TestHelper.BOOK_1);
    Iterable<Book> books = bookRepository.findAll();
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
    bookRepository.save(bookForUpdate);
    Book actualBook = bookRepository.findById(TestHelper.BOOK_ID_1).orElseThrow();
    assertThat(actualBook).hasFieldOrPropertyWithValue("title", TestHelper.BOOK_TITLE_3)
        .hasFieldOrPropertyWithValue("author", TestHelper.AUTHOR_3)
        .hasFieldOrPropertyWithValue("genre", TestHelper.GENRE_3);
  }
}
