package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.dao.BookRepository;
import ru.otus.atheneum.dao.CommentRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@SpringBootTest
@DisplayName("Для книг ")
public class BookGeneralTest {

  @Autowired
  BookService bookService;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  CommentRepository commentRepository;

  @DisplayName("метод сервиса delete() удаляет книгу из репозитория со всеми комментариями")
  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void shouldDeleteBookWithComments() {
    bookService.delete(TestHelper.BOOK_1);
    List<Book> books = bookRepository.findAll();
    List<Comment> comments = commentRepository.findAll();
    assertAll(() -> assertThat(books).containsExactly(TestHelper.BOOK_2),
        () -> assertThat(comments).filteredOn("book", TestHelper.BOOK_1).isEmpty());
  }
}
