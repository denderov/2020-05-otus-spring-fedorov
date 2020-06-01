package ru.otus.homework.quiz.domain;

import java.util.Map;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
@Deprecated
public class QuizItem {

  String question;
  @Singular
  Map<String, Boolean> answers;

}
