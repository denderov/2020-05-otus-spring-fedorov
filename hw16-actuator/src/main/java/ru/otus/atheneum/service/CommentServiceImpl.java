package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.CommentRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  public Optional<Comment> saveAndGetComment(Book book, String text) {
    Comment commentForSave = new Comment();
    commentForSave.setBook(book);
    commentForSave.setText(text);
    return Optional.of(commentRepository.save(commentForSave));
  }

  @Override
  public List<Comment> getCommentsByBook(Book commentedBook) {
    return commentRepository.findAllByBook(commentedBook);
  }

  @Override
  public void update(Comment commentForUpdate) {
    commentRepository.save(commentForUpdate);
  }

  @Override
  public void delete(Comment commentForDelete) {
    commentRepository.delete(commentForDelete);
  }
}
