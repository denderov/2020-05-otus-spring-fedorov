package ru.otus.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.TestHelper;
import ru.otus.domain.Genre;

@DisplayName("Класс GenreDaoJdbc")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

  @Autowired
  GenreDao genreDao;

  @DisplayName("возвращает жанр по id")
  @Test
  void shouldReturnGenreById() {
    Genre genre = genreDao.getById(TestHelper.GENRE_ID_1).orElse(null);
    assertThat(genre).hasFieldOrPropertyWithValue("name", TestHelper.GENRE_NAME_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Genre> genre = genreDao.getById(20200626);
    assertThat(genre).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает все жанры")
  @Test
  void shouldReturnAllGenres() {
    List<Genre> genres = genreDao.getAll();
    assertThat(genres).isEqualTo(TestHelper.GENRES);
  }

  @DisplayName("добавляет жанр")
  @Test
  void shouldInsertGenre() {
    Genre testGenre = new Genre(20200625L, "Test_genre_20200625");
    genreDao.insert(testGenre);
    Genre actualGenre = genreDao.getById(20200625L).orElse(null);
    assertThat(actualGenre).isEqualTo(testGenre);
  }

  @DisplayName("удаляет жанр по id")
  @Test
  void shouldDeleteGenreById() {
    genreDao.deleteById(TestHelper.GENRE_ID_2);
    List<Genre> genres = genreDao.getAll();
    assertThat(genres).containsExactly(TestHelper.GENRE_1);
  }
}
