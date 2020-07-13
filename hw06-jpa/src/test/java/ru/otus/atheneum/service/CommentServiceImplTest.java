package ru.otus.atheneum.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.CommentDao;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Класс GenreServiceImpl ")
class CommentServiceImplTest {

  @MockBean
  private CommentDao commentDao;

  @Autowired
  private CommentService commentService;

  @DisplayName("сохраняет комментарий и возвращает его объект")
  @Test
  void shouldSaveAndGetComment() {
    commentService.saveAndGetComment(TestHelper.BOOK_2, TestHelper.COMMENT_TEXT_3);
    verify(commentDao).insert(TestHelper.BOOK_2, TestHelper.COMMENT_TEXT_3);
  }
}