package ru.otus.atheneum.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;

  @Override
  public Optional<Comment> saveAndGetComment(Book book, String text) {
    return commentDao.insert(book, text);
  }
}
