package ru.otus.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mongo.domain.MongoBook;
import ru.otus.mongo.repository.MongoBookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoBookServiceImpl implements MongoBookService {

    private final MongoBookRepository bookRepository;

    @Override
    public List<MongoBook> getAll() {
        return bookRepository.findAll();
    }
}
