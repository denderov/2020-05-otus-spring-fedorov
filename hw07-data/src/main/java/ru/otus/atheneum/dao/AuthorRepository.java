package ru.otus.atheneum.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}

