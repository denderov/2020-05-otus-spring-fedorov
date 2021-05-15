package ru.otus.common;

public class InteractiveServiceException extends RuntimeException {

  public InteractiveServiceException(String msg) {
    super(msg);
  }

  public InteractiveServiceException(Exception ex) {
    super(ex);
  }

  public InteractiveServiceException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }
}
