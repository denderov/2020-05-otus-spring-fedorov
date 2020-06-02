package ru.otus.homework.quiz.service;

import java.io.PrintStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.QuizQuestion;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private final PrintStream printStream;
  private List<QuizQuestion> quizQuestions;

  public QuizServiceImpl(QuizDao quizDao) {
    this.quizDao = quizDao;
    printStream = System.out;
  }

  @Override
  public void readQuiz() {
    quizQuestions = quizDao.loadQuizItems();
  }

  @Override
  public void printQuizQuestions() {
    for (QuizQuestion quizQuestion :
        quizQuestions) {
      printStream.println(quizQuestion.getQuestion());
    }
  }
}
