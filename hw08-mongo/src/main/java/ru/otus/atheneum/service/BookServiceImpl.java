package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
  public Optional<Book> save(String title, Author author, Genre genre) {
    Book bookForSave = new Book();
    bookForSave.setTitle(title);
    bookForSave.setAuthor(author);
    bookForSave.setGenre(genre);
    return Optional.of(bookRepository.save(bookForSave));
  }

  @Override
  public void update(Book bookForUpdate) {
    bookRepository.save(bookForUpdate);
  }

  @Override
  public void delete(Book book) {
    bookRepository.deleteWithComments(book);
  }

  @Override
  public List<Book> getAll() {
    return bookRepository.findAll();
  }

}
