package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Genre;

public interface GenreService {

  void prepareAll();

  List<Genre> findByNamePart(String genreName);

  Optional<Genre> saveByNameAndGetGenre(String name);

  List<Genre> getPreparedGenreList();

  Optional<Genre> getGenreFromPreparedListByPosition(int genrePosition);

  void update(Genre genreForUpdate);
}
