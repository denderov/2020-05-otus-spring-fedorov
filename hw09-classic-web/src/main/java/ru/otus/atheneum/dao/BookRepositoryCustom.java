package ru.otus.atheneum.dao;

import ru.otus.domain.Book;

public interface BookRepositoryCustom {

  void deleteWithComments(Book book);

  void deleteWithComments(String id);
}
