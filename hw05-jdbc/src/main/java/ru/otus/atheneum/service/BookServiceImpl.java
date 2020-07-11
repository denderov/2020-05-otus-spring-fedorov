package ru.otus.atheneum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.BookDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;

  private BookBuilder bookBuilder;
  private List<Book> preparedBookList = new ArrayList<>();

  @Override
  public void prepareAll() {
    preparedBookList = bookDao.getAll();
  }

  @Override
  public Optional<Book> save(String title, Author author, Genre genre) {
    return bookDao.insert(title, author, genre);
  }

  @Override
  public BookService initBook() {
    bookBuilder = new BookBuilder();
    return this;
  }

  @Override
  public BookService setTitle(String bookTitle) {
    bookBuilder.setTitle(bookTitle);
    return this;
  }

  @Override
  public BookService setAuthor(Author author) {
    bookBuilder.setAuthor(author);
    return this;
  }

  @Override
  public BookService setGenre(Genre genre) {
    bookBuilder.setGenre(genre);
    return this;
  }

  @Override
  public Optional<Book> createBook() {
    return bookBuilder.createBook();
  }

  @Override
  public List<Book> getPreparedBookList() {
    return preparedBookList;
  }

  @Override
  public Optional<Book> getFromPreparedListByPosition(int bookPosition) {
    return bookPosition > 0 && bookPosition <= preparedBookList.size() ?
        Optional.of(preparedBookList.get(bookPosition - 1)) : Optional.empty();
  }

  @Override
  public void update(Book bookForUpdate) {
    bookDao.update(bookForUpdate);
  }

  @Override
  public void delete(Book book) {
    bookDao.delete(book);
  }

  public class BookBuilder {

    private String title;
    private Author author;
    private Genre genre;

    public String getTitle() {
      return title;
    }

    public BookBuilder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Author getAuthor() {
      return author;
    }

    public BookBuilder setAuthor(Author author) {
      this.author = author;
      return this;
    }

    public Genre getGenre() {
      return genre;
    }

    public BookBuilder setGenre(Genre genre) {
      this.genre = genre;
      return this;
    }

    public Optional<Book> createBook() {
      return save(title, author, genre);
    }
  }
}
