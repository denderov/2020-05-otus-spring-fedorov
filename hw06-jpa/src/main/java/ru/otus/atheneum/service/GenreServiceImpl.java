package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.atheneum.dao.GenreDao;
import ru.otus.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDao;

  @Override
  @Transactional
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    return genreDao.insert(name);
  }

  @Override
  @Transactional
  public void update(Genre genreForUpdate) {
    genreDao.update(genreForUpdate);
  }

  @Override
  @Transactional
  public void delete(Genre genre) {
    genreDao.delete(genre);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Genre> getAll() {
    return genreDao.getAll();
  }

}
