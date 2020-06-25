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

@DisplayName("Класс AuthorDao")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoImplTest {

  @Autowired
  AuthorDao authorDao;

  @DisplayName("возвращает ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorDao.getById(1L);
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
    Author testAuthor999 = new Author(999L, "Test_author_999");
    authorDao.insert(testAuthor999);
    Author actualAuthor = authorDao.getById(999L);
    assertThat(actualAuthor).isEqualTo(testAuthor999);
  }

  @DisplayName("удаляет автора по id и только его")
  @Test
  void shouldDeleteAuthor() {
    authorDao.deleteById(1L);
    List<Author> authors = authorDao.getAll();
    assertThat(authors).doesNotContain(TestHelper.AUTHOR_1).contains(TestHelper.AUTHOR_2);
  }
}
