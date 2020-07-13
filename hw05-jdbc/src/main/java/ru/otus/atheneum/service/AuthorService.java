package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;

public interface AuthorService {

  Optional<Author> saveByNameAndGetAuthor(String fullName);

  void update(Author authorForUpdate);

  void delete(Author author);

  List<Author> getAll();
}
