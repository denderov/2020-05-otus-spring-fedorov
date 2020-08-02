package ru.otus.common;

public class IOServiceException extends RuntimeException {

  private IOServiceException(String msg) {
    super(msg);
  }

  public IOServiceException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }

}
