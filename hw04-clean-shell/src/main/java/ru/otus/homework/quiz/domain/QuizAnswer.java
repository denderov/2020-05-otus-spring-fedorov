package ru.otus.homework.quiz.domain;

import lombok.Value;

@Value
public class QuizAnswer {

  String answer;
  boolean isCorrect;

}
