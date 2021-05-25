package ru.otus.atheneum.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.CommentRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyCommentList")
  public Optional<Comment> saveAndGetComment(Book book, String text) {
    Comment commentForSave = new Comment();
    commentForSave.setBook(book);
    commentForSave.setText(text);
    return Optional.of(commentRepository.save(commentForSave));
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyComment")
  public List<Comment> getCommentsByBook(Book commentedBook) {
    return commentRepository.findAllByBook(commentedBook);
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void update(Comment commentForUpdate) {
    commentRepository.save(commentForUpdate);
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void delete(Comment commentForDelete) {
    commentRepository.delete(commentForDelete);
  }

  public List<Comment> returnEmptyCommentList() {
    return Collections.emptyList();
  }

  public Optional<Comment> returnEmptyComment(String id) {
    return Optional.empty();
  }

  public void emptyFallback(Comment comment) {

  }
}
