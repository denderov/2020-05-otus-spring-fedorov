package ru.otus.homework.quiz.domain;

import java.util.List;
import lombok.Value;

@Value
public class TestRoom {

  QuizSubject quizSubject;
  List<TestQuestion> testQuestions;

}