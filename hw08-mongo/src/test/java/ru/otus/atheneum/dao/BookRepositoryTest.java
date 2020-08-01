package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import ru.otus.atheneum.TestHelper;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@DisplayName("Класс BookRepository")
@DataMongoTest
class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private GenreRepository genreRepository;

  @DisplayName("возвращает указанную книгу по id")
  @Test
  void shouldReturnBookById() {
    Book book = bookRepository.findById(TestHelper.BOOK_ID_1).orElse(null);

    assertThat(book).isEqualTo(TestHelper.BOOK_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Book> book = bookRepository.findById("20200626L");
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
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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

  @DisplayName("удаляет книгу и только ее со всеми комментариями")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldDeleteBookWithComments() {
    Iterable<Book> books = bookRepository.findAll();
    System.out.println(books);
    bookRepository.deleteWithComments(TestHelper.BOOK_1);
    books = bookRepository.findAll();
    assertThat(books).containsExactly(TestHelper.BOOK_2);
  }

  @DisplayName("изменяет книгу")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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

  @DisplayName("добавляет книгу с новыми автором и жанром")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldInsertBookWithNewAuthorAndGenre() {
    Book bookForSave = new Book();
    String test_book = "Test_book";
    bookForSave.setTitle(test_book);
    bookForSave.setAuthor(new Author("New_author"));
    bookForSave.setGenre(new Genre("New_genre"));
    Book actualBook = bookRepository.save(bookForSave);

    List<Author> authorList = authorRepository.findAllByFullName("New_author");
    List<Genre> genreList = genreRepository.findAllByName("New_genre");

    assertAll(() -> assertThat(actualBook).hasFieldOrPropertyWithValue("title", test_book),
        () -> assertThat(authorList.size()).isEqualTo(1),
        () -> assertThat(genreList.size()).isEqualTo(1));
  }
}
