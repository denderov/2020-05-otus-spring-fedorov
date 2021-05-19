package ru.otus.atheneum.dao;

public class GenreRepositoryException extends RuntimeException {

  public GenreRepositoryException(String msg) {
    super(msg);
  }

  public GenreRepositoryException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }
}
