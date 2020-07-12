package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Genre;

public interface GenreService {

  void prepareAll();

  Optional<Genre> saveByNameAndGetGenre(String name);

  List<Genre> getPreparedGenreList();

  Optional<Genre> getFromPreparedListByPosition(int genrePosition);

  void update(Genre genreForUpdate);

  void delete(Genre genre);
}
