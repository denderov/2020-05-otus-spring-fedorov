package ru.otus.atheneum.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class BookBuilderImpl implements BookBuilder {

  private final BookService bookService;

  private String title;
  private Author author;
  private Genre genre;

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public BookBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  @Override
  public Author getAuthor() {
    return author;
  }

  @Override
  public BookBuilder setAuthor(Author author) {
    this.author = author;
    return this;
  }

  @Override
  public Genre getGenre() {
    return genre;
  }

  @Override
  public BookBuilder setGenre(Genre genre) {
    this.genre = genre;
    return this;
  }

  @Override
  public Optional<Book> createBook() {
    Optional<Book> book = bookService.save(title, author, genre);
    flush();
    return book;
  }

  private void flush() {
    title = null;
    author = null;
    genre = null;
  }
}

