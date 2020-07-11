package ru.otus.atheneum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AtheneumServiceImpl implements AtheneumService {

  private final IOService ioService;
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  @Override
  public void printAllBooks() {
    bookService.prepareAll();
    ioService.println(formatObjectList(bookService.getPreparedBookList()));
  }

  @Override
  public void printAllAuthors() {
    authorService.prepareAll();
    ioService.println(formatObjectList(authorService.getPreparedAuthorList()));
  }

  @Override
  public void printAllGenres() {
    genreService.prepareAll();
    ioService.println(formatObjectList(genreService.getPreparedGenreList()));
  }

  @Override
  public void saveBook() {
    bookService.createBook();
  }

  @Override
  public void setBookTitle(String bookTitle) {
    bookService.setTitle(bookTitle);
  }

  @Override
  public void setBookAuthor(Author author) {
    bookService.setAuthor(author);
  }

  @Override
  public void setBookGenre(Genre genre) {
    bookService.setGenre(genre);
  }

  @Override
  public void setBookAuthorByPosition(int authorPosition) {
    bookService.setAuthor(authorService.getAuthorFromPreparedListByPosition(authorPosition).orElseThrow());
  }

  @Override
  public void setBookGenreByPosition(int genrePosition) {
    bookService.setGenre(genreService.getGenreFromPreparedListByPosition(genrePosition).orElseThrow());
  }

  @Override
  public void saveAuthorByName(String authorFullName) {
    Author author = authorService.saveByNameAndGetAuthor(authorFullName).orElseThrow();
    ioService.println(String.format("Сохранен автор %s", author));
  }

  @Override
  public void saveGenreByName(String genreName) {
    Genre genre = genreService.saveByNameAndGetGenre(genreName).orElseThrow();
    ioService.println(String.format("Сохранен жанр %s", genre));
  }

  @Override
  public void interactiveBookSaver() {
    bookService.initBook();
    ioService.println("Введите название книги");
    String bookTitle = ioService.readLine();
    bookService.setTitle(bookTitle);

    printAllAuthors();
    ioService.println("Введите порядковый номер автора книги");
    int authorPosition = Integer.parseInt(ioService.readLine());
    setBookAuthorByPosition(authorPosition);

    printAllGenres();
    ioService.println("Введите порядковый номер жанра книги");
    int genrePosition = Integer.parseInt(ioService.readLine());
    setBookGenreByPosition(genrePosition);

    Book book = bookService.createBook().orElseThrow();

    ioService.println(String.format("Сохранена книга %s", book));
  }

  @Override
  public void interactiveAuthorSaver() {
    ioService.println("Введите имя автора для сохранения");
    String fullName = ioService.readLine();
    saveAuthorByName(fullName);
  }

  @Override
  public void interactiveGenreSaver() {
    ioService.println("Введите название жанра для сохранения");
    String name = ioService.readLine();
    saveGenreByName(name);
  }

  private <T> String formatObjectList(List<T> ObjectList) {
    StringJoiner joiner = new StringJoiner("\n");
    for (int i = 0; i < ObjectList.size(); i++) {
      String format = String.format("%d. %s", i + 1, ObjectList.get(i).toString());
      joiner.add(format);
    }
    return joiner.toString();
  }
}
