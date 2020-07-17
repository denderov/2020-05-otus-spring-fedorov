package ru.otus.atheneum.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
