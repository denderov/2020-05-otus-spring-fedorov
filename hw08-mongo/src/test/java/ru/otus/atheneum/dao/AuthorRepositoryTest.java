package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.domain.Author;

@DisplayName("Интерфейс AuthorRepository")
@DataMongoTest
class AuthorRepositoryTest {

  @Autowired
  private AuthorRepository authorRepository;

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