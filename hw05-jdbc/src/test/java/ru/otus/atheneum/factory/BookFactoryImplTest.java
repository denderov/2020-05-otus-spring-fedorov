package ru.otus.atheneum.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.BookDao;
import ru.otus.atheneum.service.BookFactory;

@SpringBootTest
@DisplayName("Класс BookFactoryImpl")
public class BookFactoryImplTest {

  @Autowired
  BookFactory bookFactory;

  @MockBean
  BookDao bookDao;

  @DisplayName("принимает имя книги")
  @Test
  void shouldSetBookTitle() {
    bookFactory.setTitle(TestHelper.BOOK_TITLE_3);
    assertThat(bookFactory.getTitle()).isEqualTo(TestHelper.BOOK_TITLE_3);
  }

  @DisplayName("принимает автора книги")
  @Test
  void shouldSetBookAuthor() {
    bookFactory.setAuthor(TestHelper.AUTHOR_3);
    assertThat(bookFactory.getAuthor()).isEqualTo(TestHelper.AUTHOR_3);
  }

  @DisplayName("принимает жанр книги")
  @Test
  void shouldSetBookGenre() {
    bookFactory.setGenre(TestHelper.GENRE_3);
    assertThat(bookFactory.getGenre()).isEqualTo(TestHelper.GENRE_3);
  }

  @DisplayName("пишет книгу в DAO")
  @Test
  void shouldCreateBook() {
    bookFactory.setTitle(TestHelper.BOOK_TITLE_3).setAuthor(TestHelper.AUTHOR_3)
        .setGenre(TestHelper.GENRE_3).createBook();
    verify(bookDao).insert(TestHelper.BOOK_TITLE_3, TestHelper.AUTHOR_3, TestHelper.GENRE_3);
  }
}
