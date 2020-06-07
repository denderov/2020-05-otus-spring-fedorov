package ru.otus.homework.quiz.service;

import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.QuizQuestion;

@Service
@Getter
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private final IOService ioService;
  private final QuizTestService quizTestService;

  private List<QuizQuestion> quizQuestions;

  public QuizServiceImpl(@Qualifier("ioService") IOService ioService, QuizDao quizDao,
      QuizTestService quizTestService) {
    this.ioService = ioService;
    this.quizDao = quizDao;
    this.quizTestService = quizTestService;
  }

  @Override
  public void readQuiz() {
    quizQuestions = quizDao.loadQuizItems();
  }

  @Override
  public void printQuizQuestions() {
    quizQuestions.forEach(quizQuestion -> ioService.out().println(quizQuestion.getQuestion()));
  }

  @Override
  public void runTest() {
    quizTestService.runTest(quizQuestions);
  }

}
