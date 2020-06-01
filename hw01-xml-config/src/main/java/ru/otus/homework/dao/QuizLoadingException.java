package ru.otus.homework.dao;

public class QuizLoadingException extends RuntimeException {

  public QuizLoadingException(String msg) {
    super(msg);
  }

  public QuizLoadingException(Exception ex) {
    super(ex);
  }

}
