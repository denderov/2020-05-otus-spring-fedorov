package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.GenreRepository;
import ru.otus.domain.Genre;

@SpringBootTest
@DisplayName("Класс GenreServiceImpl ")
class GenreServiceImplTest {

  @MockBean
  private GenreRepository genreRepository;

  @Autowired
  private GenreService genreService;

  @DisplayName("сохраняет жанр по имени")
  @Test
  void shouldSaveGenreByName() {
    when(genreRepository.save(any()))
        .thenReturn(TestHelper.GENRE_3);
    Genre genre = genreService.saveByNameAndGetGenre(TestHelper.GENRE_NAME_3)
        .orElseThrow();
    assertThat(genre).isEqualTo(TestHelper.GENRE_3);
  }

}
