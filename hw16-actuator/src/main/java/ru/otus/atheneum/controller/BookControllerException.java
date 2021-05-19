package ru.otus.atheneum.controller;

public class BookControllerException extends RuntimeException {

  public BookControllerException(String msg) {
    super(msg);
  }

  public BookControllerException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }
}
