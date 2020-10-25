package ru.otus.atheneum.dao;

public class AuthorRepositoryException extends RuntimeException {

  public AuthorRepositoryException(String msg) {
    super(msg);
  }

  public AuthorRepositoryException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }
}
