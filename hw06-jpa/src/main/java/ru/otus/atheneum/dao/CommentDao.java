package ru.otus.atheneum.dao;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentDao {

  Optional<Comment> getById(long commentId);

  List<Comment> getAll();

  void delete(Comment comment);

  Optional<Comment> insert(Book book, String text);

  void update(Comment comment);

  List<Comment> getCommentsByBook(Book commentedBook);
}
