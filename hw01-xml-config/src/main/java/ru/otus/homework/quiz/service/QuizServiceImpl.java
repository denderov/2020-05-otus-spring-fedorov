package ru.otus.homework.quiz.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.QuizQuestion;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private final IOService ioService;
  private List<QuizQuestion> quizQuestions;

  @Override
  public void readQuiz() {
    quizQuestions = quizDao.loadQuizItems();
  }

  @Override
  public void printQuizQuestions() {
    for (QuizQuestion quizQuestion :
        quizQuestions) {
      ioService.println(quizQuestion.getQuestion());
    }
  }
}
