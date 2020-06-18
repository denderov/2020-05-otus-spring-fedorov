package ru.otus.homework.quiz.dao;

import java.util.List;
import ru.otus.homework.quiz.domain.QuizQuestion;

public interface QuizDao {

  List<QuizQuestion> loadQuizItems();

  List<QuizQuestion> loadQuizItems(String name);
}
