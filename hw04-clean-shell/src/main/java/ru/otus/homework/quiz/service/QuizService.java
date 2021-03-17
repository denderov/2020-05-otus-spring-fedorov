package ru.otus.homework.quiz.service;

import ru.otus.homework.logging.Loggable;

public interface QuizService {

  @Loggable
  void readQuiz(String quizName);

  void runTest();

  boolean isQuizQuestionsLoaded();
}
