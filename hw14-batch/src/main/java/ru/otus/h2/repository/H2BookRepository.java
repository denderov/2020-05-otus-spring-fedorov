package ru.otus.h2.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.h2.domain.H2Book;

import java.util.List;

public interface H2BookRepository extends JpaRepository<H2Book, Long> {
    @Override
    @EntityGraph("author-n-genre")
    List<H2Book> findAll();
}
