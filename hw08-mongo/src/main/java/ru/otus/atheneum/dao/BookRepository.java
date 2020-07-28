package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

  List<Book> findByAuthor(Author author);
}
