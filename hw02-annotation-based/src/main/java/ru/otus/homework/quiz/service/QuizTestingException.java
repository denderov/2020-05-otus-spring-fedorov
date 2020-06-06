package ru.otus.homework.quiz.service;

public class QuizTestingException extends RuntimeException {

  public QuizTestingException(String msg) {
    super(msg);
  }

  public QuizTestingException(Exception ex) {
    super(ex);
  }
}
