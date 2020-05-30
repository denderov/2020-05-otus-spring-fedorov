package ru.otus.homework.domain;

import java.util.Map;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class QuizItem {

  String question;
  @Singular
  Map<String, Boolean> answers;

}
