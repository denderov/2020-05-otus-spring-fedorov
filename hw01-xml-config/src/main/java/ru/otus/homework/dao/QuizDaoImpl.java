package ru.otus.homework.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import ru.otus.homework.domain.QuizItem;

public class QuizDaoImpl implements QuizDao {

  private static final List<String> TRUE_VALUES = List.of("1", "TRUE");

  private final Reader reader;

  QuizDaoImpl(String defaultQuizCsv) {
    this.reader = new InputStreamReader(
        this.getClass().getResourceAsStream("/" + defaultQuizCsv));
  }

  QuizDaoImpl(Reader reader) {
    this.reader = reader;
  }

  @Override
  public List<QuizItem> loadQuizItems() {
    return getQuizItemsFromReader(reader);
  }

  private List<QuizItem> getQuizItemsFromReader(Reader reader) {

    List<QuizItem> quizItems = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(reader)) {

      String line;
      final var quizItemBuilder = QuizItem.builder();

      while ((line = br.readLine()) != null) {

        String[] values = line.split(",");

        if (values.length % 2 == 0) {
          throw new QuizLoadingException("Проверка четности. "
              + "Строка должна содержать вопрос и пары ответ - "
              + "флаг корректности ответа, разделенные запятыми.");
        }

        quizItemBuilder.question(values[0]);
        for (int i = 1; i < values.length; i = i + 2) {
          boolean isCorrectAnswer = TRUE_VALUES.contains(values[i + 1].toUpperCase());
          quizItemBuilder.answer(values[i], isCorrectAnswer);
        }
        quizItems.add(quizItemBuilder.build());

      }
    } catch (IOException original) {
      var e = new QuizLoadingException("Ошибка ввода-вывода.");
      e.initCause(original);
      throw e;
    }

    return quizItems;

  }

}
