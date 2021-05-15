package ru.otus.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.h2.domain.H2Genre;

public interface H2GenreRepository extends JpaRepository<H2Genre, Long> {
    H2Genre findByExtId(String id);
}
