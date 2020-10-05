package ru.otus.atheneum.service;

public class EntityConverterException extends RuntimeException {

  public EntityConverterException(String msg) {
    super(msg);
  }

  public EntityConverterException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }
}
