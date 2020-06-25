package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@DisplayName("Класс BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

  @Autowired
  BookDao bookDao;

  @DisplayName("возвращает указанную книгу по id")
  @Test
  void shouldReturnBookById() {

  }

}
