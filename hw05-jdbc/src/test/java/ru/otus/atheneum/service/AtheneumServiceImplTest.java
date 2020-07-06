package ru.otus.atheneum.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.common.IOService;

@SpringBootTest
@DisplayName("Класс AtheneumServiceImpl")
public class AtheneumServiceImplTest {

  @MockBean
  private IOService ioService;
  @Autowired
  private AtheneumService atheneumService;

  @DisplayName("печатает список книг")
  @Test
  void shouldPrintAllBooks() {
    atheneumService.printAllBooks();
    verify(ioService).println(
        "1. Book(id=1, title=Test_book_1, author=Author(id=101, fullName=Test_author_1), genre=Genre(id=201, name=Test_genre_1))\n"
            + "2. Book(id=2, title=Test_book_2, author=Author(id=102, fullName=Test_author_2), genre=Genre(id=202, name=Test_genre_2))");
  }

  @DisplayName("печатает список авторов")
  @Test
  void shouldPrintAllAuthors() {
    atheneumService.printAllAuthors();
    verify(ioService).println("1. Author(id=101, fullName=Test_author_1)\n"
        + "2. Author(id=102, fullName=Test_author_2)\n"
        + "3. Author(id=103, fullName=Test_author_3)");
  }

}
