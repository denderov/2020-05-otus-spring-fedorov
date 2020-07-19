package ru.otus.atheneum.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.TestHelper;
import ru.otus.atheneum.dao.CommentRepository;
import ru.otus.domain.Comment;

@SpringBootTest
@DisplayName("Класс GenreServiceImpl ")
class CommentServiceImplTest {

  @MockBean
  private CommentRepository commentRepository;

  @Autowired
  private CommentService commentService;

  @DisplayName("сохраняет комментарий и возвращает его объект")
  @Test
  void shouldSaveAndGetComment() {
    when(commentRepository.save(refEq(TestHelper.COMMENT_2, "id", "dateTime")))
        .thenReturn(TestHelper.COMMENT_2);
    Comment comment = commentService.saveAndGetComment(TestHelper.BOOK_2, TestHelper.COMMENT_TEXT_2)
        .orElseThrow();
    assertAll(() -> verify(commentRepository).save(refEq(TestHelper.COMMENT_2, "id", "dateTime")),
        () -> assertThat(comment).hasFieldOrPropertyWithValue("book", TestHelper.BOOK_2)
            .hasFieldOrPropertyWithValue("text", TestHelper.COMMENT_TEXT_2));
  }

  @DisplayName("возвращает список комментариев по указанной книге")
  @Test
  void shouldPrintCommentsByBook() {
    when(commentRepository.findAllByBook(TestHelper.BOOK_1))
        .thenReturn(List.of(TestHelper.COMMENT_1));
    List<Comment> comments = commentService.getCommentsByBook(TestHelper.BOOK_1);
    assertThat(comments).containsExactly(TestHelper.COMMENT_1);
  }
}