package ru.otus.atheneum.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.GenreRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyGenre")
  public Optional<Genre> saveByNameAndGetGenre(String name) {
    Genre genreForSave = new Genre();
    genreForSave.setName(name);
    return Optional.of(genreRepository.save(genreForSave));
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void update(Genre genreForUpdate) {
    genreRepository.save(genreForUpdate);
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void delete(Genre genre) {
    genreRepository.delete(genre);
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyGenreList")
  public List<Genre> getAll() {
    return genreRepository.findAll();
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyGenre")
  public Optional<Genre> getById(String id) {
    return genreRepository.findById(id);
  }

  public List<Genre> returnEmptyGenreList() {
    return Collections.emptyList();
  }

  public Optional<Genre> returnEmptyGenre(String id) {
    return Optional.empty();
  }

  public void emptyFallback(Genre genre) {

  }
}
