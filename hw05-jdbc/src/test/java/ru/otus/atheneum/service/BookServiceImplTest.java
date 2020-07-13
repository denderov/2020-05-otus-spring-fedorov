package ru.otus.atheneum.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.BookDao;

@SpringBootTest
@DisplayName("Класс BookServiceImpl ")
public class BookServiceImplTest {

  @MockBean
  private BookDao bookDao;

  @Autowired
  private BookService bookService;

  @DisplayName("вызывает DAO для сохранения")
  @Test
  void shouldSaveBook() {
    bookService.save(TestHelper.BOOK_TITLE_3, TestHelper.AUTHOR_3, TestHelper.GENRE_3);
    verify(bookDao, times(1))
        .insert(TestHelper.BOOK_TITLE_3, TestHelper.AUTHOR_3, TestHelper.GENRE_3);
  }
}
