package ru.otus.homework.quiz.domain;

import java.util.Set;
import java.util.TreeSet;
import lombok.Value;

@Value
public class TestQuestion {

  QuizQuestion quizQuestion;
  Set<Integer> receivedAnswers = new TreeSet<>();

  public TestQuestion addReceivedAnswer(Integer receivedAnswer) {
    receivedAnswers.add(receivedAnswer);
    return this;
  }

}
