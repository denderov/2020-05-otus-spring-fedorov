package ru.otus.dao;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Book;

public interface BookDao {

  Optional<Book> getById(long id);

  List<Book> getAll();

  void insert(Book book);

  void delete(long id);
}
