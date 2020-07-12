package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;

public interface AuthorService {

  Optional<Author> saveByNameAndGetAuthor(String fullName);

  void prepareAll();

  List<Author> getPreparedAuthorList();

  Optional<Author> getFromPreparedListByPosition(int authorPosition);

  void update(Author authorForUpdate);

  void delete(Author author);
}
