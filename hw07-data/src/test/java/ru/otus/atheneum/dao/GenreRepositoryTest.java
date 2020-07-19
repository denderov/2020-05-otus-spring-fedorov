package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.TestHelper;
import ru.otus.domain.Genre;

@DisplayName("Интерейс GenreRepository")
@DataJpaTest
public class GenreRepositoryTest {

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private TestEntityManager em;

  @DisplayName("возвращает жанр по id")
  @Test
  void shouldReturnGenreById() {
    Genre genre = genreRepository.findById(TestHelper.GENRE_ID_1).orElse(null);
    assertThat(genre).hasFieldOrPropertyWithValue("name", TestHelper.GENRE_NAME_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Genre> genre = genreRepository.findById(20200626L);
    assertThat(genre).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает все жанры")
  @Test
  void shouldReturnAllGenres() {
    Iterable<Genre> genres = genreRepository.findAll();
    assertThat(genres).isEqualTo(TestHelper.GENRES);
  }

  @DisplayName("добавляет жанр")
  @Test
  void shouldInsertGenre() {
    Genre genreForSave = new Genre();
    String test_genre_20200625 = "Test_genre_20200625";
    genreForSave.setName(test_genre_20200625);
    Genre actualGenre = genreRepository.save(genreForSave);
    assertThat(actualGenre).hasFieldOrPropertyWithValue("name", test_genre_20200625);
  }

  @DisplayName("удаляет жанр и только его")
  @Test
  void shouldDeleteGenre() {
    genreRepository.delete(TestHelper.GENRE_3);
    Iterable<Genre> genres = genreRepository.findAll();
    assertThat(genres).doesNotContain(TestHelper.GENRE_3)
        .contains(TestHelper.GENRE_1, TestHelper.GENRE_2);
  }

  @DisplayName("не удаляет жанр, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedGenre() {
    genreRepository.delete(TestHelper.GENRE_1);
    Exception e = assertThrows(PersistenceException.class,
        () -> em.flush());
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
    genreRepository.save(genreForUpdate);
    Genre actualGenre = genreRepository.findById(TestHelper.GENRE_ID_1).orElseThrow();
    assertThat(actualGenre).hasFieldOrPropertyWithValue("name", TestHelper.GENRE_NAME_3);
  }
}
