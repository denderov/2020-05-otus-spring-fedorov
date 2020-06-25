package ru.otus.dao;

import java.util.List;
import ru.otus.domain.Genre;

public interface GenreDao {

  Genre getById(long id);

  List<Genre> getAll();

  void insert(Genre genre);

  void deleteById(long id);
}
