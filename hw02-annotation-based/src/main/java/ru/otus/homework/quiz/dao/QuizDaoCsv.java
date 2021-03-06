package ru.otus.homework.quiz.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;

@Repository
public class QuizDaoCsv implements QuizDao {

  private static final List<String> TRUE_VALUES = List.of("1", "TRUE");

  private final BufferedReader reader;

  @Autowired
  public QuizDaoCsv(@Value("${csv.name}") String defaultQuizCsv) {
    reader = new BufferedReader(
        new InputStreamReader(this.getClass().getResourceAsStream("/" + defaultQuizCsv)));

  }

  @Override
  public List<QuizQuestion> loadQuizItems() {
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
