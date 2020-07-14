package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentService {

  Optional<Comment> saveAndGetComment(Book book, String text);

  List<Comment> getCommentsByBook(Book commentedBook);

  void update(Comment commentForUpdate);

  void delete(Comment commentForDelete);
}
