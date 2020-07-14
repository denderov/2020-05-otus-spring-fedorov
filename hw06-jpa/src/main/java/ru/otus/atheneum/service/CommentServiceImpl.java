package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.atheneum.dao.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;

  @Override
  @Transactional
  public Optional<Comment> saveAndGetComment(Book book, String text) {
    return commentDao.insert(book, text);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Comment> getCommentsByBook(Book commentedBook) {
    return commentDao.getCommentsByBook(commentedBook);
  }

  @Override
  @Transactional
  public void update(Comment commentForUpdate) {
    commentDao.update(commentForUpdate);
  }

  @Override
  @Transactional
  public void delete(Comment commentForDelete) {
    commentDao.delete(commentForDelete);
  }
}
