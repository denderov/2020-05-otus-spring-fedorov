package ru.otus.homework.domain;

import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс QuizItem")
class QuizItemTest {

  private static final String QUESTION = "Question";
  private static final String ANSWER_1 = "Correct answer";
  private static final boolean IS_CORRECT_ANSWER_1 = true;
  private static final String ANSWER_2 = "Incorrect answer";
  private static final boolean IS_CORRECT_ANSWER_2 = false;
  private static final Map<String, Boolean> ANSWERS =
      Map.of(ANSWER_1, IS_CORRECT_ANSWER_1, ANSWER_2, IS_CORRECT_ANSWER_2);

  @DisplayName("корректно создается билдером")
  @Test
  void shouldHaveBeenCorrectDoneByBuilder() {
    QuizItem quizItem = QuizItem.builder().question(QUESTION).answers(ANSWERS).build();
    assertAll(
        () -> assertThat(quizItem.getQuestion()).isEqualTo(QUESTION),
        () -> assertThat(quizItem.getAnswers())
            .containsExactly(entry(ANSWER_1, IS_CORRECT_ANSWER_1),
                entry(ANSWER_2, IS_CORRECT_ANSWER_2))
    );
  }

  @DisplayName("корректно создается билдером построчно")
  @Test
  void shouldHaveBeenCorrectDoneBySingularityBuilder() {
    QuizItem quizItem = QuizItem.builder()
        .question(QUESTION)
        .answer(ANSWER_1, IS_CORRECT_ANSWER_1)
        .answer(ANSWER_2, IS_CORRECT_ANSWER_2)
        .build();
    assertAll(
        () -> assertThat(quizItem.getQuestion()).isEqualTo(QUESTION),
        () -> assertThat(quizItem.getAnswers())
            .containsExactly(entry(ANSWER_1, IS_CORRECT_ANSWER_1),
                entry(ANSWER_2, IS_CORRECT_ANSWER_2))
    );
  }

  @DisplayName("корректно создается конструктором")
  @Test
  void shouldHaveBeenCorrectDoneByConstructor() {
    QuizItem quizItem = new QuizItem(QUESTION, ANSWERS);
    assertAll(
        () -> assertThat(quizItem.getQuestion()).isEqualTo(QUESTION),
        () -> assertThat(quizItem.getAnswers())
            .containsExactly(entry(ANSWER_1, IS_CORRECT_ANSWER_1),
                entry(ANSWER_2, IS_CORRECT_ANSWER_2))
    );
  }

}
