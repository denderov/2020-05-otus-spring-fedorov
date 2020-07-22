package ru.otus.atheneum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
