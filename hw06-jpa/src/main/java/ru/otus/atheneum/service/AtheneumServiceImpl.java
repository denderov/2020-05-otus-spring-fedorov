package ru.otus.atheneum.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
  public final CommentService commentService;

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
    Book book;
    try {
      book = bookService.createBook().orElseThrow();
      ioService.println(String.format("Сохранена книга %s", book));
    } catch (NoSuchElementException e) {
      ioService.println("Книга не выбрана и не была сохранена!");
    }
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
  public void initBook() {
    bookService.initBook();
  }

  @Override
  public Optional<Book> getBookFromPreparedListByPosition(int bookPosition) {
    return bookService.getFromPreparedListByPosition(bookPosition);
  }

  @Override
  public Optional<Author> getAuthorFromPreparedListByPosition(int authorPosition) {
    return authorService.getFromPreparedListByPosition(authorPosition);
  }

  @Override
  public Optional<Genre> getGenreFromPreparedListByPosition(int genrePosition) {
    return genreService.getFromPreparedListByPosition(genrePosition);
  }

  @Override
  public void updateBook(Book book) {
    bookService.update(book);
  }

  @Override
  public void updateAuthor(Author author) {
    authorService.update(author);
  }

  @Override
  public void updateGenre(Genre genre) {
    genreService.update(genre);
  }

  @Override
  public void deleteAuthor(Author author) {
    authorService.delete(author);
  }

  @Override
  public void deleteGenre(Genre genre) {
    genreService.delete(genre);
  }

  @Override
  public void deleteBook(Book book) {
    bookService.delete(book);
  }

  @Override
  public void saveComment(Book book, String text) {
    commentService.saveAndGetComment(book, text);
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
