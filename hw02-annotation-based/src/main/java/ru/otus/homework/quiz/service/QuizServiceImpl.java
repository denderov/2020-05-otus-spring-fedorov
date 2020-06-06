package ru.otus.homework.quiz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.QuizQuestion;

@Service
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private final IOService ioService;
  private List<QuizQuestion> quizQuestions;

  public QuizServiceImpl(QuizDao quizDao, @Qualifier("ioService") IOService ioService) {
    this.quizDao = quizDao;
    this.ioService = ioService;
  }

  @Override
  public void readQuiz() {
    quizQuestions = quizDao.loadQuizItems();
  }

  @Override
  public void printQuizQuestions() {
    quizQuestions.forEach(quizQuestion -> ioService.out().println(quizQuestion.getQuestion()));
  }
}
