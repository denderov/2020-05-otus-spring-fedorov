package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.atheneum.TestHelper;
import ru.otus.domain.Author;

@DisplayName("Интерфейс AuthorRepository")
@DataMongoTest
@EnableConfigurationProperties
class AuthorRepositoryTest {

  @Autowired
  private AuthorRepository authorRepository;

  @DisplayName("возвращает ожидаемого автора по id")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author actualAuthor = authorRepository.findById(TestHelper.AUTHOR_1.getId()).orElse(null);
    assertThat(actualAuthor).hasFieldOrPropertyWithValue("fullName", TestHelper.AUTHOR_FULL_NAME_1);
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

}