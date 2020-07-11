package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;

public interface AuthorService {

  List<Author> findByFullNamePart(String fullNamePart);

  Optional<Author> saveByNameAndGetAuthor(String fullName);

  void prepareAll();

  List<Author> getPreparedAuthorList();

  Optional<Author> getAuthorFromPreparedListByPosition(int authorPosition);

  void updateAuthor(Author authorForUpdate);
}
