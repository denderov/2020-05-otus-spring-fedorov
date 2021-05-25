package ru.otus.atheneum.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.BookRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  @HystrixCommand(fallbackMethod = "returnZero")
  public long count() {
    return bookRepository.count();
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyBook")
  public Optional<Book> save(String title, Author author, Genre genre) {
    Book bookForSave = new Book();
    bookForSave.setTitle(title);
    bookForSave.setAuthor(author);
    bookForSave.setGenre(genre);
    return Optional.of(bookRepository.save(bookForSave));
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void update(Book bookForUpdate) {
    bookRepository.save(bookForUpdate);
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void delete(Book book) {
    bookRepository.delete(book);
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void delete(String id) {
    bookRepository.deleteWithComments(id);
  }

  @SneakyThrows
  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyBookList")
  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyBook")
  public Optional<Book> getById(String id) {
    return bookRepository.findById(id);
  }

  public List<Book> returnEmptyBookList() {
    return Collections.emptyList();
  }

  public Optional<Book> returnEmptyBook(String id) {
    return Optional.empty();
  }

  public long returnZero() {
    return 0;
  }

  public void emptyFallback(Book book) {

  }

  public void emptyFallback(String id) {

  }

}
