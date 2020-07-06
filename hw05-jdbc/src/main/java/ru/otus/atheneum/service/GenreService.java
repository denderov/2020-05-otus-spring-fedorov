package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Genre;

public interface GenreService {

  List<Genre> getAll();

  List<Genre> findByNamePart(String genreName);

  Optional<Genre> saveByNameAndGetGenre(String name);
}
