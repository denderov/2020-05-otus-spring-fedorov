package ru.otus.atheneum.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.TestHelper;
import ru.otus.domain.Comment;

@DisplayName("Класс CommentDaoJpa")
@DataJpaTest
@Import(CommentDaoJpa.class)
public class CommentDaoJpaTest {

  @Autowired
  CommentDao commentDao;

  @DisplayName("возвращает комментарий по id")
  @Test
  void shouldReturnCommentById() {
    Comment comment = commentDao.getById(TestHelper.COMMENT_ID_1).orElseThrow();
    assertThat(comment).isEqualTo(TestHelper.COMMENT_1);
  }

  @DisplayName("возвращает пустой Optional по несуществующему id")
  @Test
  void shouldReturnEmptyOptionalByMissingId() {
    Optional<Comment> comment = commentDao.getById(20200712);
    assertThat(comment).isEqualTo(Optional.empty());
  }

  @DisplayName("возвращает все комментарии")
  @Test
  void shouldReturnAllComments() {
    List<Comment> comments = commentDao.getAll();
    assertThat(comments).isEqualTo(TestHelper.COMMENTS);
  }

  @DisplayName("добавляет комментарий")
  @Test
  void shouldInsertComment() {
    String test_comment_20200712 = "Test_comment_20200712";
    Comment actualComment = commentDao.insert(TestHelper.BOOK_2, test_comment_20200712)
        .orElseThrow();
    assertThat(actualComment).hasFieldOrPropertyWithValue("text", test_comment_20200712);
  }

  @DisplayName("удаляет комментарий и только его")
  @Test
  void shouldDeleteComment() {
    commentDao.delete(TestHelper.COMMENT_1);
    List<Comment> comments = commentDao.getAll();
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
    commentDao.update(commentForUpdate);
    Comment actualComment = commentDao.getById(TestHelper.COMMENT_ID_1).orElseThrow();
    assertThat(actualComment).hasFieldOrPropertyWithValue("text", TestHelper.COMMENT_TEXT_2);
  }
}
