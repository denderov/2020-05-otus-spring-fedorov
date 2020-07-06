package ru.otus.atheneum.dao;

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
    String test_genre_20200625 = "Test_genre_20200625";
    Genre actualGenre = genreDao.insert(test_genre_20200625).orElseThrow();
    assertThat(actualGenre).hasFieldOrPropertyWithValue("name", test_genre_20200625);
  }

  @DisplayName("удаляет жанр по id и только его")
  @Test
  void shouldDeleteGenreById() {
    genreDao.deleteById(TestHelper.GENRE_ID_3);
    List<Genre> genres = genreDao.getAll();
    assertThat(genres).doesNotContain(TestHelper.GENRE_3)
        .contains(TestHelper.GENRE_1, TestHelper.GENRE_2);
  }

  @DisplayName("не удаляет жанр, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedGenre() {
    genreDao.deleteById(TestHelper.GENRE_ID_1);
    genreDao.deleteById(TestHelper.GENRE_ID_2);
    List<Genre> genres = genreDao.getAll();
    assertThat(genres).isEqualTo(TestHelper.GENRES);
  }

  @DisplayName("возвращает жанры по имени")
  @Test
  void shouldGetByNamePart() {
    List<Genre> genres = genreDao.getByNamePart(TestHelper.GENRE_NAME_1);
    assertThat(genres).containsExactly(TestHelper.GENRE_1);
  }
}
