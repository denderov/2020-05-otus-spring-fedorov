package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.GenreDao;
import ru.otus.domain.Genre;

@DisplayName("Класс GenreServiceImpl ")
@SpringBootTest
public class GenreServiceImplTest {

  @MockBean
  private GenreDao genreDao;

  @Autowired
  private GenreService genreService;

  @DisplayName("возвращает полный список авторов")
  @Test
  void shouldReturnAllAuthors() {
    when(genreDao.getAll()).thenReturn(TestHelper.GENRES);
    genreService.prepareAll();
    List<Genre> genres = genreService.getPreparedGenreList();
    assertThat(genres).isEqualTo(TestHelper.GENRES);
  }

  @DisplayName("возвращает авторов по части имени")
  @Test
  void shouldReturnGenresByNamePart() {
    when(genreDao.getByNamePart(TestHelper.GENRE_NAME_1))
        .thenReturn(List.of(TestHelper.GENRE_1));
    List<Genre> genres = genreService.findByNamePart(TestHelper.GENRE_NAME_1);
    assertThat(genres).containsExactly(TestHelper.GENRE_1);
  }

  @DisplayName("сохраняет жанр по имени")
  @Test
  void shouldSaveGenreByName() {
    when(genreDao.insert(TestHelper.GENRE_NAME_3))
        .thenReturn(Optional.of(TestHelper.GENRE_3));
    Genre genre = genreService.saveByNameAndGetGenre(TestHelper.GENRE_NAME_3)
        .orElseThrow();
    assertThat(genre).isEqualTo(TestHelper.GENRE_3);
  }

}
