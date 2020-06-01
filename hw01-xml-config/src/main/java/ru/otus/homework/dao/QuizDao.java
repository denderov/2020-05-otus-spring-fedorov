package ru.otus.homework.dao;

import java.util.List;
import ru.otus.homework.domain.QuizItem;

public interface QuizDao {

  List<QuizItem> loadQuizItems();

}
