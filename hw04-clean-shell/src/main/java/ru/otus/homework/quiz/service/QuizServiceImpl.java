package ru.otus.homework.quiz.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.logging.Loggable;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.QuizQuestion;

@Service
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private final IOService ioService;
  private final QuizTestingService quizTestingService;

  private List<QuizQuestion> quizQuestions;

  public QuizServiceImpl(IOService ioService, QuizDao quizDao,
      QuizTestingService quizTestingService) {

    this.quizDao = quizDao;
    this.ioService = ioService;
    this.quizTestingService = quizTestingService;
  }

  @Loggable
  @Override
  public void readQuiz(String quizName) {
    quizQuestions = quizDao.loadQuizItems(quizName);
  }

  @Override
  public void runTest() {
    quizTestingService.runTest(quizQuestions);
  }

  @Override
  public boolean isQuizQuestionsLoaded() {
    return quizQuestions == null;
  }

}
