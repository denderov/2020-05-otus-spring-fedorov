package ru.otus.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.h2.domain.H2Genre;
import ru.otus.mongo.domain.MongoGenre;

public interface MongoGenreRepository extends MongoRepository<MongoGenre, String> {
}
