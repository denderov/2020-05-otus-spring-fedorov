package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.atheneum.dao.BookDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;

  @Override
  @Transactional
  public Optional<Book> save(String title, Author author, Genre genre) {
    return bookDao.insert(title, author, genre);
  }

  @Override
  @Transactional
  public void update(Book bookForUpdate) {
    bookDao.update(bookForUpdate);
  }

  @Override
  @Transactional
  public void delete(Book book) {
    bookDao.delete(book);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Book> getAll() {
    return bookDao.getAll();
  }

}
