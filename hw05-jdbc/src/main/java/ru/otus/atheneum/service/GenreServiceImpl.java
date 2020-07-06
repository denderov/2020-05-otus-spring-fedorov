package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.GenreDao;
import ru.otus.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDao;

  @Override
  public List<Genre> getAll() {
    return genreDao.getAll();
  }

  @Override
  public List<Genre> findByNamePart(String genreName) {
    return genreDao.getByNamePart(genreName);
  }

  @Override
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    return genreDao.insert(name);
  }

}
