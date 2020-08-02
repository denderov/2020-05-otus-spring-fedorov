package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import ru.otus.atheneum.TestHelper;
import ru.otus.domain.Genre;

@DisplayName("Интерейс GenreRepository")
@DataMongoTest
@ComponentScan("ru.otus.events")
class GenreRepositoryTest {

  @Autowired
  private GenreRepository genreRepository;

  @DisplayName("возвращает жанр по id")
  @Test
  void shouldReturnGenreById() {
    Genre genre = genreRepository.findById(TestHelper.GENRE_ID_1).orElse(null);
    assertThat(genre).hasFieldOrPropertyWithValue("name", TestHelper.GENRE_NAME_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Genre> genre = genreRepository.findById("20200626L");
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
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldInsertGenre() {
    Genre genreForSave = new Genre();
    String test_genre_20200625 = "Test_genre_20200625";
    genreForSave.setName(test_genre_20200625);
    Genre actualGenre = genreRepository.save(genreForSave);
    assertThat(actualGenre).hasFieldOrPropertyWithValue("name", test_genre_20200625);
  }

  @DisplayName("удаляет жанр и только его")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldDeleteGenre() {
    genreRepository.delete(TestHelper.GENRE_3);
    Iterable<Genre> genres = genreRepository.findAll();
    assertThat(genres).doesNotContain(TestHelper.GENRE_3)
        .contains(TestHelper.GENRE_1, TestHelper.GENRE_2);
  }

  @DisplayName("не удаляет жанр, если на него есть ссылка")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldNotDeleteLinkedGenre() {
    Exception e = assertThrows(GenreRepositoryException.class,
        () -> genreRepository.delete(TestHelper.GENRE_1));
    String expectedMessage = "Нельзя удалить жанр у которого есть книги!";
    String actualMessage = e.getMessage();
    assertThat(actualMessage).contains(expectedMessage);
  }

  @DisplayName("изменяет жанр")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldUpdateGenre() {
    Genre genreFromDb = TestHelper.GENRE_1;
    Genre genreForUpdate = new Genre(genreFromDb.getId(), genreFromDb.getName());
    genreForUpdate.setName(TestHelper.GENRE_NAME_3);
    genreRepository.save(genreForUpdate);
    Genre actualGenre = genreRepository.findById(TestHelper.GENRE_ID_1).orElseThrow();
    assertThat(actualGenre).hasFieldOrPropertyWithValue("name", TestHelper.GENRE_NAME_3);
  }
}
