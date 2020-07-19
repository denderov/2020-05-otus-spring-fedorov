package ru.otus.common;

public class IOServiceException extends RuntimeException {

  public IOServiceException(String msg) {
    super(msg);
  }

  public IOServiceException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }

}
