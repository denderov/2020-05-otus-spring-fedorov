package ru.otus.h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.h2.domain.H2Genre;
import ru.otus.h2.repository.H2GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class H2GenreServiceImpl implements H2GenreService {

    private final H2GenreRepository h2GenreRepository;

    @Override
    public List<H2Genre> getAll() {
        return h2GenreRepository.findAll();
    }
}
