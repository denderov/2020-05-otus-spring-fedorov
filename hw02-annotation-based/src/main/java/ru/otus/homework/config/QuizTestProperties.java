package ru.otus.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@lombok.Value
@Component
public class QuizTestProperties {

  int testQuestionsCount;
  int passPercent;

  public QuizTestProperties(@Value("${test.questionCount}") int testQuestionsCount,
      @Value("${test.passPercent}") int passPercent) {

    this.testQuestionsCount = testQuestionsCount;
    this.passPercent = passPercent;

  }

}
