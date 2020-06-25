package ru.otus.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
    Author actualAuthor = authorDao.getById(TestHelper.AUTHOR_ID_1);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_1);
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
    Author actualAuthor = authorDao.getById(20200625L);
    assertThat(actualAuthor).isEqualTo(testAuthor20200625);
  }

  @DisplayName("удаляет автора по id и только его")
  @Test
  void shouldDeleteAuthor() {
    authorDao.deleteById(1L);
    List<Author> authors = authorDao.getAll();
    assertThat(authors).doesNotContain(TestHelper.AUTHOR_1).contains(TestHelper.AUTHOR_2);
  }
}
