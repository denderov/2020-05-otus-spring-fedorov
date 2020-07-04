package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.AuthorDao;
import ru.otus.common.IOService;
import ru.otus.domain.Author;

@DisplayName("Класс AuthorServiceImpl ")
@SpringBootTest
@ContextConfiguration(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

  @MockBean
  private IOService ioService;
  @MockBean
  private AuthorDao authorDao;

  @Autowired
  private AuthorService authorService;

  @DisplayName("возвращает автора по имени (если он есть в базе)")
  @Test
  void shouldReturnAuthorByFullName() {
    when(authorDao.getByFullName(TestHelper.AUTHOR_FULL_NAME_1))
        .thenReturn(Optional.of(TestHelper.AUTHOR_1));
    Author author = authorService.getByFullName(TestHelper.AUTHOR_FULL_NAME_1);
    verify(authorDao, never()).insert(TestHelper.AUTHOR_FULL_NAME_3);
    assertThat(author).isEqualTo(TestHelper.AUTHOR_1);
  }

  @DisplayName("возвращает автора по имени (и добавляет его в базу)")
  @Test
  void shouldReturnNewAuthorByFullName() {
    when(authorDao.getByFullName(TestHelper.AUTHOR_FULL_NAME_3))
        .thenReturn(Optional.empty());
    when(authorDao.insert(TestHelper.AUTHOR_FULL_NAME_3))
        .thenReturn(Optional.of(TestHelper.AUTHOR_3));
    Author author = authorService.getByFullName(TestHelper.AUTHOR_FULL_NAME_3);
    verify(authorDao, times(1)).insert(TestHelper.AUTHOR_FULL_NAME_3);
    assertThat(author).isEqualTo(TestHelper.AUTHOR_3);
  }
}
