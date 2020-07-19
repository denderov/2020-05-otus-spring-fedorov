package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.TestHelper;
import ru.otus.domain.Author;

@DisplayName("Интерфейс AuthorRepository")
@DataJpaTest
public class AuthorRepositoryTest {

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private TestEntityManager em;

  @DisplayName("возвращает ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorRepository.findById(TestHelper.AUTHOR_ID_1).orElse(null);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Author> author = authorRepository.findById(20200626L);
    assertThat(author).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает полный список авторов")
  @Test
  void shouldReturnAllAuthors() {
    Iterable<Author> authors = authorRepository.findAll();
    assertThat(authors).isEqualTo(TestHelper.AUTHORS);
  }

  @DisplayName("добавляет автора")
  @Test
  void shouldInsertAuthor() {
    Author authorForSave = new Author();
    String fullName = "Test_author_20200625";
    authorForSave.setFullName(fullName);
    Author testAuthor20200625 = authorRepository.save(authorForSave);
    assertThat(testAuthor20200625).hasFieldOrPropertyWithValue("fullName", fullName);
  }

  @DisplayName("удаляет автора и только его")
  @Test
  void shouldDeleteAuthor() {
    authorRepository.delete(TestHelper.AUTHOR_3);
    Iterable<Author> authors = authorRepository.findAll();
    assertThat(authors).doesNotContain(TestHelper.AUTHOR_3)
        .contains(TestHelper.AUTHOR_1, TestHelper.AUTHOR_2);
  }

  @DisplayName("не удаляет автора, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedAuthor() {
    authorRepository.delete(TestHelper.AUTHOR_1);
    assertThatThrownBy(() -> em.flush()).hasCauseInstanceOf(ConstraintViolationException.class);
  }

  @DisplayName("изменяет автора")
  @Test
  void shouldUpdateAuthor() {
    Author authorFromDb = TestHelper.AUTHOR_1;
    Author authorForUpdate = new Author(authorFromDb.getId(), authorFromDb.getFullName());
    authorForUpdate.setFullName(TestHelper.AUTHOR_FULL_NAME_3);
    Author actualAuthor = authorRepository.save(authorForUpdate);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_3);
  }
}
