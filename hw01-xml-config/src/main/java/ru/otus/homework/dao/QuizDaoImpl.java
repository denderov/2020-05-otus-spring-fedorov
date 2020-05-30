package ru.otus.homework.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import ru.otus.homework.domain.QuizItem;

public class QuizDaoImpl implements QuizDao {

  private static final List<String> TRUE_VALUES = List.of("1", "TRUE");

  private final String defaultQuizPath;

  public QuizDaoImpl(String defaultQuizPath) {
    this.defaultQuizPath = defaultQuizPath;
  }

  @SneakyThrows
  @Override
  public List<QuizItem> processQuizItemsFromDefaultPath() {
    return getQuizItemsFromReader(new FileReader(defaultQuizPath));
  }

  @Override
  public List<QuizItem> getQuizItemsFromReader(Reader reader) throws IOException {

    List<QuizItem> quizItems = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(reader)) {
      String line;
      final var quizItemBuilder = QuizItem.builder();
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        quizItemBuilder.question(values[0]);
        for (int i = 1; i < values.length; i = i + 2) {
          boolean isCorrectAnswer = TRUE_VALUES.contains(values[i + 1].toUpperCase());
          quizItemBuilder.answer(values[i], isCorrectAnswer);
        }
        quizItems.add(quizItemBuilder.build());
      }
    }

    return quizItems;

  }

}
