package ru.otus.atheneum.service;

import static org.mockito.ArgumentMatchers.anyString;
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
  private static IOService ioService;
  @Autowired
  private AtheneumService atheneumService;

  @DisplayName("печатает список книг")
  @Test
  void shouldPrintAllBooks() {
    atheneumService.printAllBooks();
    verify(ioService).println(anyString());
  }

}
