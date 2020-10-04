package ru.otus.atheneum.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

@Repository
public interface GenreReactiveRepository extends ReactiveMongoRepository<Genre, String> {

}

