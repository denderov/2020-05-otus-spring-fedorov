package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import ru.otus.atheneum.TestHelper;
import ru.otus.domain.Author;

@DisplayName("Интерфейс AuthorRepository")
@DataMongoTest
//ну нет роллбэка в монге :(
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorRepositoryTest {

  @Autowired
  private AuthorRepository authorRepository;

  @DisplayName("возвращает ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorRepository.findById(TestHelper.AUTHOR_1.getId()).orElse(null);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Author> author = authorRepository.findById("missingid");
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
    authorRepository.deleteWhenFree(TestHelper.AUTHOR_3);
    Iterable<Author> authors = authorRepository.findAll();
    assertThat(authors).doesNotContain(TestHelper.AUTHOR_3)
        .contains(TestHelper.AUTHOR_1, TestHelper.AUTHOR_2);
  }

  @DisplayName("не удаляет автора, если на него есть ссылка")
  @Test
  void shouldNotDeleteLinkedAuthor() {
    assertThatThrownBy(() -> authorRepository.deleteWhenFree(TestHelper.AUTHOR_1))
        .isInstanceOf(AuthorRepositoryException.class);
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