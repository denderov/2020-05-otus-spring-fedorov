package ru.otus.mongo.service;

import org.springframework.stereotype.Service;
import ru.otus.mongo.domain.MongoBook;

import java.util.List;

public interface MongoBookService {
    List<MongoBook> getAll();
}
