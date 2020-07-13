package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.AuthorDao;
import ru.otus.domain.Author;

@DisplayName("Класс AuthorServiceImpl ")
@SpringBootTest
public class AuthorServiceImplTest {

  @MockBean
  private AuthorDao authorDao;

  @Autowired
  private AuthorService authorService;

  @DisplayName("сохраняет автора по имени")
  @Test
  void shouldSaveAuthorByName() {
    when(authorDao.insert(TestHelper.AUTHOR_FULL_NAME_3))
        .thenReturn(Optional.of(TestHelper.AUTHOR_3));
    Author author = authorService.saveByNameAndGetAuthor(TestHelper.AUTHOR_FULL_NAME_3)
        .orElseThrow();
    assertThat(author).isEqualTo(TestHelper.AUTHOR_3);
  }
}
