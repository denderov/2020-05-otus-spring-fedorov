package ru.otus.atheneum.dao;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface BookDao {

  Optional<Book> getById(long id);

  List<Book> getAll();

  void delete(Book book);

  Optional<Book> insert(String title, Author author, Genre genre);

  void update(Book book);
}
