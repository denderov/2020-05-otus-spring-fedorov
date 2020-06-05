package ru.otus.homework.quiz.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;

@Repository
public class QuizDaoCsv implements QuizDao {

  private static final List<String> TRUE_VALUES = List.of("1", "TRUE");

  private final IOService ioService;
  private Reader reader;

  public QuizDaoCsv(@Qualifier("ioDaoService") IOService ioService) {
    this.ioService = ioService;
  }

  @Override
  public List<QuizQuestion> loadQuizItems() {
    reader = new InputStreamReader(ioService.in());
    return getQuizItemsFromReader(reader);
  }

  private List<QuizQuestion> getQuizItemsFromReader(Reader reader) {

    List<QuizQuestion> quizQuestions = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(reader)) {

      String line;

      while ((line = br.readLine()) != null) {

        String[] values = line.split(",");
        List<QuizAnswer> answers = new ArrayList<>();

        if (values.length % 2 == 0) {
          throw new QuizLoadingException("Проверка четности. "
              + "Строка должна содержать вопрос и пары ответ - "
              + "флаг корректности ответа, разделенные запятыми.");
        }

        for (int i = 1; i < values.length; i = i + 2) {
          boolean isCorrectAnswer = TRUE_VALUES.contains(values[i + 1].toUpperCase());
          answers.add(new QuizAnswer(values[i], isCorrectAnswer));
        }
        quizQuestions.add(new QuizQuestion(values[0], answers));

      }
    } catch (IOException original) {
      var e = new QuizLoadingException("Ошибка ввода-вывода.");
      e.initCause(original);
      throw e;
    }

    return quizQuestions;

  }

}
