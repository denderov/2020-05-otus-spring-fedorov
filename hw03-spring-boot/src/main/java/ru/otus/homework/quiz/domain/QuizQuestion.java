package ru.otus.homework.quiz.domain;

import java.util.List;
import lombok.Value;

@Value
public class QuizQuestion {

  String question;
  List<QuizAnswer> answers;

}
