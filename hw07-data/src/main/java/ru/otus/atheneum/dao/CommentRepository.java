package ru.otus.atheneum.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

  Iterable<Comment> findAllByBook(Book commentedBook);

  void deleteAllByBook(Book commentedBook);
}
