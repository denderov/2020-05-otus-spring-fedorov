package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Genre;

public interface GenreService {

  Optional<Genre> saveByNameAndGetGenre(String name);

  void update(Genre genreForUpdate);

  void delete(Genre genre);

  List<Genre> getAll();

  Optional<Genre> getById(String id);
}
