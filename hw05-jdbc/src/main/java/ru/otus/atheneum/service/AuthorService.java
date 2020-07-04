package ru.otus.atheneum.service;

import ru.otus.domain.Author;

public interface AuthorService {

  Author getByFullName(String fullName);

}
