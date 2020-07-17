package ru.otus.atheneum.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
