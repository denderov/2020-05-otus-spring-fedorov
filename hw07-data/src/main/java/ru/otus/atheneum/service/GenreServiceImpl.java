package ru.otus.atheneum.service;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.atheneum.dao.GenreRepository;
import ru.otus.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  @Transactional
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    Genre genreForSave = new Genre();
    genreForSave.setName(name);
    return Optional.of(genreRepository.save(genreForSave));
  }

  @Override
  @Transactional
  public void update(Genre genreForUpdate) {
    genreRepository.save(genreForUpdate);
  }

  @Override
  @Transactional
  public void delete(Genre genre) {
    genreRepository.delete(genre);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Genre> getAll() {
    return Lists.newArrayList(genreRepository.findAll());
  }

}
