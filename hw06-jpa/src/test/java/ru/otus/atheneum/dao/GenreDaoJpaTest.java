package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.TestHelper;
import ru.otus.domain.Genre;

@DisplayName("Класс GenreDaoJpa")
@DataJpaTest
@Import(GenreDaoJpa.class)
public class GenreDaoJpaTest {

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

  @DisplayName("удаляет жанр и только его")
  @Test
  void shouldDeleteGenre() {
    genreDao.delete(TestHelper.GENRE_3);
    List<Genre> genres = genreDao.getAll();
    assertThat(genres).doesNotContain(TestHelper.GENRE_3)
        .contains(TestHelper.GENRE_1, TestHelper.GENRE_2);
  }

  @DisplayName("не удаляет жанр, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedGenre() {
    Exception e = assertThrows(PersistenceException.class,
        () -> genreDao.delete(TestHelper.GENRE_1));
    String expectedMessage = "could not execute statement";
    String actualMessage = e.getMessage();
    assertThat(actualMessage).contains(expectedMessage);
  }

  @DisplayName("изменяет жанр")
  @Test
  void shouldUpdateGenre() {
    Genre genreFromDb = TestHelper.GENRE_1;
    Genre genreForUpdate = new Genre(genreFromDb.getId(), genreFromDb.getName());
    genreForUpdate.setName(TestHelper.GENRE_NAME_3);
    genreDao.update(genreForUpdate);
    Genre actualGenre = genreDao.getById(TestHelper.GENRE_ID_1).orElseThrow();
    assertThat(actualGenre).hasFieldOrPropertyWithValue("name", TestHelper.GENRE_NAME_3);
  }
}
