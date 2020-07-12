package ru.otus.atheneum.service;

import java.util.ArrayList;
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

  private List<Genre> preparedGenreList = new ArrayList<>();

  @Override
  @Transactional(readOnly = true)
  public void prepareAll() {
    preparedGenreList = genreDao.getAll();
  }

  @Override
  @Transactional
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    return genreDao.insert(name);
  }

  @Override
  public List<Genre> getPreparedGenreList() {
    return preparedGenreList;
  }

  @Override
  public Optional<Genre> getFromPreparedListByPosition(int genrePosition) {
    return genrePosition > 0 && genrePosition <= preparedGenreList.size() ?
        Optional.of(preparedGenreList.get(genrePosition - 1)) : Optional.empty();
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

}
