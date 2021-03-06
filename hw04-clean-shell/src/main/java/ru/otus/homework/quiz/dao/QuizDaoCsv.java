package ru.otus.homework.quiz.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;

public class QuizDaoCsv implements QuizDao {

  private static final List<String> TRUE_VALUES = List.of("1", "TRUE");

  private final Locale locale;
  private BufferedReader reader;
  private final String defaultQuizName;

  public QuizDaoCsv(String defaultQuizName, Locale locale) {
    this.defaultQuizName = defaultQuizName;
    this.locale = locale;
  }

  private BufferedReader getReaderOfFile(String quizName) {
    String csvFileName = String
        .format("%s_%s.csv", quizName, locale);
    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(
            this.getClass()
                .getResourceAsStream("/" + csvFileName)));
    return bufferedReader;
  }

  @Override
  public List<QuizQuestion> loadQuizItems() {
    this.setReader(getReaderOfFile(defaultQuizName));
    return getQuizItemsFromReader();
  }

  private void setReader(BufferedReader bufferedReader) {
    this.reader = bufferedReader;
  }

  @Override
  public List<QuizQuestion> loadQuizItems(String name) {
    if (name == null || name.isEmpty()) {
      this.setReader(getReaderOfFile(defaultQuizName));
    } else {
      this.setReader(getReaderOfFile(name));
    }
    return getQuizItemsFromReader();
  }

  private List<QuizQuestion> getQuizItemsFromReader() {

    List<QuizQuestion> quizQuestions = new ArrayList<>();

    String line;

    try {
      while ((line = reader.readLine()) != null) {

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
    } catch (IOException e) {
      throw new QuizLoadingException("Ошибка чтения CSV из ресурса", e);
    }

    return quizQuestions;

  }

}
