package ru.otus.atheneum.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;

@Repository
public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String> {

}

