package ru.otus.homework.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.QuizItem;

@DisplayName("Класс QuizDaoImpl")
class QuizDaoImplTest {

  @SneakyThrows
  @DisplayName("корректно читает вопросы из ридера в QuizItem")
  @Test
  void shouldCorrectReadQuiz() {

    List<QuizItem> quizItemsFromBuilder = List.of(
        QuizItem.builder()
            .question("Question1")
            .answers(Map.of("Answer1", true, "Answer2", false))
            .build(),
        QuizItem.builder()
            .question("Question2")
            .answers(Map.of("Answer2", true, "Answer1", false))
            .build()
    );

    QuizDao quizDao = new QuizDaoImpl("");

    String csvString = "Question1,Answer1,true,Answer2,false"
        + "\n"
        + "Question2,Answer1,0,Answer2,1";

    List<QuizItem> quizItemsFromDao = quizDao.getQuizItemsFromReader(new StringReader(csvString));

    assertThat(quizItemsFromDao).isEqualTo(quizItemsFromBuilder);

  }

}
