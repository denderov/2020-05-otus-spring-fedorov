package ru.otus.atheneum.dao;

import ru.otus.domain.Author;

public interface AuthorRepositoryCustom {

  void deleteWhenFree(Author author);
}
