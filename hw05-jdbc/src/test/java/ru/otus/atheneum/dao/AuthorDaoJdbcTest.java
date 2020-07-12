package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
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
    String fullName = "Test_author_20200625";
    Author testAuthor20200625 = authorDao.insert(fullName).orElse(null);
    assertThat(testAuthor20200625).hasFieldOrPropertyWithValue("fullName", fullName);
  }

  @DisplayName("удаляет автора и только его")
  @Test
  void shouldDeleteAuthor() {
    authorDao.delete(TestHelper.AUTHOR_3);
    List<Author> authors = authorDao.getAll();
    assertThat(authors).doesNotContain(TestHelper.AUTHOR_3)
        .contains(TestHelper.AUTHOR_1, TestHelper.AUTHOR_2);
  }

  @DisplayName("не удаляет автора, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedAuthor() {
    Exception e = assertThrows(DataAccessException.class,
        () -> authorDao.delete(TestHelper.AUTHOR_1));
    String expectedMessage = "Referential integrity constraint violation";
    String actualMessage = e.getMessage();
    assertThat(actualMessage).contains(expectedMessage);
  }

  @DisplayName("изменяет автора")
  @Test
  void shouldUpdateAuthor() {
    Author authorFromDb = TestHelper.AUTHOR_1;
    Author authorForUpdate = new Author(authorFromDb.getId(), authorFromDb.getFullName());
    authorForUpdate.setFullName(TestHelper.AUTHOR_FULL_NAME_3);
    authorDao.update(authorForUpdate);
    Author actualAuthor = authorDao.getById(TestHelper.AUTHOR_ID_1).orElseThrow();
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_3);
  }
}
