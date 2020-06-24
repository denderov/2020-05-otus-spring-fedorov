package ru.otus.dao;

import static org.assertj.core.api.Assertions.assertThat;

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
  AuthorDaoJdbc authorDaoJdbc;

  @DisplayName("возвращать ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorDaoJdbc.getById(1L);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", "Vasya");
  }
}
