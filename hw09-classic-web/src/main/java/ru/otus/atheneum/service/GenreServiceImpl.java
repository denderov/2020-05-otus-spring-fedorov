package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.GenreRepository;
import ru.otus.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    Genre genreForSave = new Genre();
    genreForSave.setName(name);
    return Optional.of(genreRepository.save(genreForSave));
  }

  @Override
  public void update(Genre genreForUpdate) {
    genreRepository.save(genreForUpdate);
  }

  @Override
  public void delete(Genre genre) {
    genreRepository.delete(genre);
  }

  @Override
  public List<Genre> getAll() {
    return genreRepository.findAll();
  }

  @Override
  public Optional<Genre> getById(String id) {
    return genreRepository.findById(id);
  }

}
