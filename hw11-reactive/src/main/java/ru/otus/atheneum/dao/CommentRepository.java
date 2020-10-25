package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

  List<Comment> findAllByBook(Book commentedBook);

  void deleteAllByBook(Book commentedBook);

  void deleteAllByBookId(String id);
}
