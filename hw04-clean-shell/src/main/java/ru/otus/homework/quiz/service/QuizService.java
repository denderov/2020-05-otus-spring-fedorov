package ru.otus.homework.quiz.service;

import ru.otus.homework.logging.Loggable;

public interface QuizService {

  void readQuiz();

  @Loggable
  void readQuiz(String quizName);

  void printQuizQuestions();

  void runTest();

  boolean isQuizQuestionsLoaded();
}
