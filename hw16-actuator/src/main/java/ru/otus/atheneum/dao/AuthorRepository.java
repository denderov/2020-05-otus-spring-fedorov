package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {

  List<Author> findAllByFullName(String fullName);
}

