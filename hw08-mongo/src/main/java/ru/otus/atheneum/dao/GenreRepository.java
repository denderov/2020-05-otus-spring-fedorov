package ru.otus.atheneum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
