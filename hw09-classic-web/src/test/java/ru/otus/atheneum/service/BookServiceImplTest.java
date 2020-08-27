package ru.otus.atheneum.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.dao.BookRepository;

@SpringBootTest
@DisplayName("Класс BookServiceImpl ")
class BookServiceImplTest {

  @MockBean
  private BookRepository bookRepository;

  @Autowired
  private BookService bookService;

  @DisplayName("вызывает DAO для сохранения")
  @Test
  void shouldSaveBook() {
    when(bookRepository.save(any())).thenReturn(TestHelper.BOOK_1);
    bookService.save(TestHelper.BOOK_TITLE_1, TestHelper.AUTHOR_1, TestHelper.GENRE_1);
    verify(bookRepository, times(1))
        .save(any());
  }

}
