package ru.otus.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

@DisplayName("Класс AuthorDao")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoTest {

  @Autowired
  AuthorDao authorDao;

  @DisplayName("возвращать ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorDao.getById(1L);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", "Vasya");
  }

  @DisplayName("возвращать полный список авторов")
  @Test
  void shouldReturnAllAuthors() {
    List<Author> authors = authorDao.getAll();
    assertThat(authors).containsExactlyInAnyOrder(new Author(1, "Vasya"));
  }
}
