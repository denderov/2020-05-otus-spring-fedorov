package ru.otus.mongo.service;

import ru.otus.mongo.domain.MongoGenre;

import java.util.List;

public interface MongoGenreService {
    List<MongoGenre> getAll();
}
