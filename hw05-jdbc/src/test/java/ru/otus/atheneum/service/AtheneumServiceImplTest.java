package ru.otus.atheneum.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.common.IOService;

@SpringBootTest
@DisplayName("Класс AtheneumServiceImpl")
public class AtheneumServiceImplTest {

  @MockBean
  private IOService ioService;

  @MockBean
  private BookService bookService;

  @MockBean
  private AuthorService authorService;

  @MockBean
  private GenreService genreService;

  @Autowired
  private AtheneumService atheneumService;

  @DisplayName("печатает список книг")
  @Test
  void shouldPrintAllBooks() {
    when(bookService.getPreparedBookList()).thenReturn(TestHelper.BOOKS);
    atheneumService.printAllBooks();
    verify(ioService).println(
        "1. Book(id=1, title=Test_book_1, author=Author(id=101, fullName=Test_author_1), genre=Genre(id=201, name=Test_genre_1))\n"
            + "2. Book(id=2, title=Test_book_2, author=Author(id=102, fullName=Test_author_2), genre=Genre(id=202, name=Test_genre_2))");
  }

  @DisplayName("печатает список авторов")
  @Test
  void shouldPrintAllAuthors() {
    when(authorService.getPreparedAuthorList()).thenReturn(TestHelper.AUTHORS);
    atheneumService.printAllAuthors();
    verify(ioService).println("1. Author(id=101, fullName=Test_author_1)\n"
        + "2. Author(id=102, fullName=Test_author_2)\n"
        + "3. Author(id=103, fullName=Test_author_3)");
  }

  @DisplayName("печатает список жанров")
  @Test
  void shouldPrintAllGenre() {
    when(genreService.getPreparedGenreList()).thenReturn(TestHelper.GENRES);
    atheneumService.printAllGenres();
    verify(ioService).println("1. Genre(id=201, name=Test_genre_1)\n"
        + "2. Genre(id=202, name=Test_genre_2)\n"
        + "3. Genre(id=203, name=Test_genre_3)");
  }

  @DisplayName("работает с полями встроенного в BookServiceImpl билдера книг")
  @Test
  void shouldSetBookBuilderFields() {
    atheneumService.setBookTitle(TestHelper.BOOK_TITLE_1);
    atheneumService.setBookAuthor(TestHelper.AUTHOR_1);
    atheneumService.setBookGenre(TestHelper.GENRE_1);
    assertAll(() -> verify(bookService).setTitle(TestHelper.BOOK_TITLE_1),
        () -> verify(bookService).setAuthor(TestHelper.AUTHOR_1),
        () -> verify(bookService).setGenre(TestHelper.GENRE_1));
  }

  @DisplayName("сохраняет книгу")
  @Test
  void shouldSaveBook() {
    atheneumService.saveBook();
    verify(bookService).createBook();
  }

  @DisplayName("устанавливает автора для книги по позиции")
  @Test
  void shouldSetBookAuthorByPosition() {
    when(authorService.getFromPreparedListByPosition(1))
        .thenReturn(Optional.of(TestHelper.AUTHOR_1));
    atheneumService.setBookAuthorByPosition(1);
    verify(bookService).setAuthor(TestHelper.AUTHOR_1);
  }

  @DisplayName("устанавливает жанр для книги по позиции")
  @Test
  void shouldSetBookGenreByPosition() {
    when(genreService.getFromPreparedListByPosition(1)).thenReturn(Optional.of(TestHelper.GENRE_1));
    atheneumService.setBookGenreByPosition(1);
    verify(bookService).setGenre(TestHelper.GENRE_1);
  }

  @DisplayName("сохраняет автора")
  @Test
  void shouldSaveAuthor() {
    when(authorService.saveByNameAndGetAuthor(TestHelper.AUTHOR_FULL_NAME_3))
        .thenReturn(Optional.of(TestHelper.AUTHOR_3));
    atheneumService.saveAuthorByName(TestHelper.AUTHOR_FULL_NAME_3);
    verify(authorService).saveByNameAndGetAuthor(TestHelper.AUTHOR_FULL_NAME_3);
  }

  @DisplayName("сохраняет жанр")
  @Test
  void shouldSaveGenre() {
    when(genreService.saveByNameAndGetGenre(TestHelper.GENRE_NAME_3))
        .thenReturn(Optional.of(TestHelper.GENRE_3));
    atheneumService.saveGenreByName(TestHelper.GENRE_NAME_3);
    verify(genreService).saveByNameAndGetGenre(TestHelper.GENRE_NAME_3);
  }


}
