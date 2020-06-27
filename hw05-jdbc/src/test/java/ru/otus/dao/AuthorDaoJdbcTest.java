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
import ru.otus.domain.Author;

@DisplayName("Класс AuthorDaoJdbc")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest {

  @Autowired
  AuthorDao authorDao;

  @DisplayName("возвращает ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorDao.getById(TestHelper.AUTHOR_ID_1).orElse(null);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Author> author = authorDao.getById(20200626);
    assertThat(author).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает полный список авторов")
  @Test
  void shouldReturnAllAuthors() {
    List<Author> authors = authorDao.getAll();
    assertThat(authors).isEqualTo(TestHelper.AUTHORS);
  }

  @DisplayName("добавляет автора")
  @Test
  void shouldInsertAuthor() {
    Author testAuthor20200625 = new Author(20200625L, "Test_author_20200625");
    authorDao.insert(testAuthor20200625);
    Author actualAuthor = authorDao.getById(20200625L).orElse(null);
    assertThat(actualAuthor).isEqualTo(testAuthor20200625);
  }

  @DisplayName("удаляет автора по id и только его")
  @Test
  void shouldDeleteAuthor() {
    authorDao.deleteById(TestHelper.AUTHOR_ID_3);
    List<Author> authors = authorDao.getAll();
    assertThat(authors).doesNotContain(TestHelper.AUTHOR_3)
        .contains(TestHelper.AUTHOR_1, TestHelper.AUTHOR_2);
  }

  @DisplayName("не удаляет автора, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedAuthor() {
    authorDao.deleteById(TestHelper.AUTHOR_ID_1);
    authorDao.deleteById(TestHelper.AUTHOR_ID_2);
    List<Author> authors = authorDao.getAll();
    assertThat(authors).isEqualTo(TestHelper.AUTHORS);
  }
}
