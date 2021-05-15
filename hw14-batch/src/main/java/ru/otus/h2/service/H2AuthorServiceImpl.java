package ru.otus.h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.h2.domain.H2Author;
import ru.otus.h2.repository.H2AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class H2AuthorServiceImpl implements H2AuthorService {

    private final H2AuthorRepository h2AuthorRepository;

    @Override
    public List<H2Author> getAll() {
        return h2AuthorRepository.findAll();
    }
}
