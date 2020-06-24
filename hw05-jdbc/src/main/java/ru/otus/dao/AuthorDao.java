package ru.otus.dao;

import ru.otus.domain.Author;

public interface AuthorDao {

  Author getById(long id);
}
