package ru.otus.atheneum.service;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.atheneum.dao.CommentRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public Optional<Comment> saveAndGetComment(Book book, String text) {
    Comment commentForSave = new Comment();
    commentForSave.setBook(book);
    commentForSave.setText(text);
    return Optional.of(commentRepository.save(commentForSave));
  }

  @Override
  @Transactional(readOnly = true)
  public List<Comment> getCommentsByBook(Book commentedBook) {
    return Lists.newArrayList(commentRepository.findAllByBook(commentedBook));
  }

  @Override
  @Transactional
  public void update(Comment commentForUpdate) {
    commentRepository.save(commentForUpdate);
  }

  @Override
  @Transactional
  public void delete(Comment commentForDelete) {
    commentRepository.delete(commentForDelete);
  }
}
