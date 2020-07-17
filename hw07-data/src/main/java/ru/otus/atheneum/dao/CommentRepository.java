package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

  List<Comment> findAllByBook(Book commentedBook);
}
