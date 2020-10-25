package ru.otus.atheneum.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

@Repository
public interface AuthorReactiveRepository extends ReactiveMongoRepository<Author, String> {

}

