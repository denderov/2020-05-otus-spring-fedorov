package ru.otus.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mongo.domain.MongoAuthor;


public interface MongoAuthorRepository extends MongoRepository<MongoAuthor, String> {
}
