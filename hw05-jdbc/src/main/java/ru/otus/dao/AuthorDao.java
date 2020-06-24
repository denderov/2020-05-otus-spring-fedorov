package ru.otus.dao;

import java.util.List;
import ru.otus.domain.Author;

public interface AuthorDao {

  Author getById(long id);

  List<Author> getAll();
}
