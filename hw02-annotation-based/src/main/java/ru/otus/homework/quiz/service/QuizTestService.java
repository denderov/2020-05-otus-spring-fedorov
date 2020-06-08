package ru.otus.homework.quiz.service;

import java.util.List;
import ru.otus.homework.quiz.domain.QuizQuestion;

public interface QuizTestService {

  void runTest(List<QuizQuestion> quizQuestions);
}
