package ru.otus.homework.quiz.dao;

public class QuizLoadingException extends RuntimeException {

  public QuizLoadingException(String msg) {
    super(msg);
  }

  public QuizLoadingException(Exception ex) {
    super(ex);
  }

  public QuizLoadingException(String msg, Throwable original) {
    this(msg);
    initCause(original);
  }

}
