package ru.otus.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mongo.domain.MongoGenre;
import ru.otus.mongo.repository.MongoGenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoGenreServiceImpl implements MongoGenreService {

    private final MongoGenreRepository mongoGenreRepository;

    @Override
    public List<MongoGenre> getAll() {
        return mongoGenreRepository.findAll();
    }
}
