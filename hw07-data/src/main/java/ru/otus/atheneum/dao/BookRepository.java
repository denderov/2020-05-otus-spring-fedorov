package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

  @Override
  @EntityGraph("author-n-genre")
  List<Book> findAll();
}
