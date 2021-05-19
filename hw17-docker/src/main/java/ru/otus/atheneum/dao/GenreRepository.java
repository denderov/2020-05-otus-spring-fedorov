package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

  List<Genre> findAllByName(String name);
}
