package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;

public interface AuthorService {

  List<Author> getByFullNamePart(String fullNamePart);

  Optional<Author> saveByNameAndGetAuthor(String fullName);
}
