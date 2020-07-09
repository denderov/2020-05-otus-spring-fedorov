package ru.otus.atheneum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class AtheneumServiceImpl implements AtheneumService {

  private final IOService ioService;
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  private List<Book> bookList = new ArrayList<>();
  private List<Author> authorList = new ArrayList<>();
  private List<Genre> genreList = new ArrayList<>();

  @Override
  public void printAllBooks() {
    bookList = bookService.getAll();
    ioService.println(formatObjectList(bookList));
  }

  @Override
  public void printAllAuthors() {
    authorList = authorService.getAll();
    ioService.println(formatObjectList(authorList));
  }

  @Override
  public void printAllGenres() {
    genreList = genreService.getAll();
    ioService.println(formatObjectList(genreList));
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
    int authorIndex = authorPosition - 1;
    bookService.setAuthor(authorList.get(authorIndex));
  }

  @Override
  public void setBookGenreByPosition(int genrePosition) {
    int genreIndex = genrePosition - 1;
    bookService.setGenre(genreList.get(genreIndex));
  }

  @Override
  public void saveAuthorByName(String authorFullName) {
    authorList.add(authorService.saveByNameAndGetAuthor(authorFullName).orElseThrow());
    ioService.println(formatObjectList(authorList));
  }

  @Override
  public void saveGenreByName(String genreName) {
    genreList.add(genreService.saveByNameAndGetGenre(genreName).orElseThrow());
    ioService.println(formatObjectList(genreList));
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
