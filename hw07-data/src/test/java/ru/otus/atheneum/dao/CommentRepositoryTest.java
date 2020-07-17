package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.TestHelper;
import ru.otus.domain.Comment;

@DisplayName("Interface CommentRepository")
@DataJpaTest
public class CommentRepositoryTest {

  @Autowired
  CommentRepository commentRepository;

  @DisplayName("возвращает комментарий по id")
  @Test
  void shouldReturnCommentById() {
    Comment comment = commentRepository.findById(TestHelper.COMMENT_ID_1).orElseThrow();
    assertThat(comment).isEqualTo(TestHelper.COMMENT_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Comment> comment = commentRepository.findById(20200712L);
    assertThat(comment).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает все комментарии")
  @Test
  void shouldReturnAllComments() {
    Iterable<Comment> comments = commentRepository.findAll();
    assertThat(comments).isEqualTo(TestHelper.COMMENTS);
  }

  @DisplayName("добавляет комментарий")
  @Test
  void shouldInsertComment() {
    Comment commentForSave = new Comment();
    String test_comment_20200712 = "Test_comment_20200712";
    commentForSave.setText(test_comment_20200712);
    commentForSave.setBook(TestHelper.BOOK_2);
    Comment actualComment = commentRepository.save(commentForSave);
    assertThat(actualComment).hasFieldOrPropertyWithValue("text", test_comment_20200712);
  }

  @DisplayName("удаляет комментарий и только его")
  @Test
  void shouldDeleteComment() {
    commentRepository.delete(TestHelper.COMMENT_1);
    Iterable<Comment> comments = commentRepository.findAll();
    assertThat(comments).doesNotContain(TestHelper.COMMENT_1)
        .contains(TestHelper.COMMENT_2);
  }

  @DisplayName("изменяет комментарий")
  @Test
  void shouldUpdateComment() {
    Comment commentFromDb = TestHelper.COMMENT_1;
    Comment commentForUpdate = new Comment(commentFromDb.getId(), LocalDateTime.now(),
        TestHelper.BOOK_1, commentFromDb.getText());
    commentForUpdate.setText(TestHelper.COMMENT_TEXT_2);
    commentRepository.save(commentForUpdate);
    Comment actualComment = commentRepository.findById(TestHelper.COMMENT_ID_1).orElseThrow();
    assertThat(actualComment).hasFieldOrPropertyWithValue("text", TestHelper.COMMENT_TEXT_2);
  }
}
