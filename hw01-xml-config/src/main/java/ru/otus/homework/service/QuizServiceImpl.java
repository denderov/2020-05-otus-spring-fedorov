package ru.otus.homework.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.QuizItem;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private List<QuizItem> quizItems;

  @Override
  public void readQuiz() {
    quizItems = quizDao.loadQuizItems();
  }

  @Override
  public void printQuizQuestions() {
    for (QuizItem quizItem :
        quizItems) {
      System.out.println(quizItem.getQuestion());
    }
  }
}
