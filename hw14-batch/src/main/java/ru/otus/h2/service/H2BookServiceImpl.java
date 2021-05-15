package ru.otus.h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.h2.domain.H2Book;
import ru.otus.h2.repository.H2BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class H2BookServiceImpl implements H2BookService {

    private final H2BookRepository h2BookRepository;

    @Override
    public List<H2Book> getAll() {
        return h2BookRepository.findAll();
    }
}
