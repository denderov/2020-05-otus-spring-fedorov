package ru.otus.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mongo.domain.MongoAuthor;
import ru.otus.mongo.repository.MongoAuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoAuthorServiceImpl implements MongoAuthorService {

    private final MongoAuthorRepository mongoAuthorRepository;

    @Override
    public List<MongoAuthor> getAll() {
        return mongoAuthorRepository.findAll();
    }
}
