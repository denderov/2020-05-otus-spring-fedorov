package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface BookService {

  Optional<Book> save(String title, Author author, Genre genre);

  void update(Book bookForUpdate);

  void delete(Book book);

  void delete(String id);

  List<Book> getAll();
}
