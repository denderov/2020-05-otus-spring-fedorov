package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByBook(Book commentedBook);

  void deleteAllByBook(Book commentedBook);
}
