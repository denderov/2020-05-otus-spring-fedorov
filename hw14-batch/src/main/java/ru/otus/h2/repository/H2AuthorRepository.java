package ru.otus.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.h2.domain.H2Author;


public interface H2AuthorRepository extends JpaRepository<H2Author, Long> {
    H2Author findByExtId(String id);
}
