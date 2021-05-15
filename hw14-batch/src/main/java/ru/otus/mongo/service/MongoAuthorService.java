package ru.otus.mongo.service;

import org.springframework.stereotype.Service;
import ru.otus.mongo.domain.MongoAuthor;

import java.util.List;

public interface MongoAuthorService {
    List<MongoAuthor> getAll();
}
