package ru.otus.atheneum.service;

import java.util.ArrayList;
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

  private List<Genre> preparedGenreList = new ArrayList<>();

  @Override
  public void prepareAll() {
    preparedGenreList = genreDao.getAll();
  }

  @Override
  public List<Genre> findByNamePart(String genreName) {
    return genreDao.getByNamePart(genreName);
  }

  @Override
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    return genreDao.insert(name);
  }

  @Override
  public List<Genre> getPreparedGenreList() {
    return preparedGenreList;
  }

  @Override
  public Optional<Genre> getGenreFromPreparedListByPosition(int genrePosition) {
    return genrePosition > 0 && genrePosition <= preparedGenreList.size() ?
        Optional.of(preparedGenreList.get(genrePosition - 1)) : Optional.empty();
  }

}
