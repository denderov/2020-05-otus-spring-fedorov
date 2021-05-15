package ru.otus.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mongo.domain.MongoBook;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {
}
