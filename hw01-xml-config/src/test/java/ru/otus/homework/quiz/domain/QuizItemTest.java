package ru.otus.homework.quiz.domain;

import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс QuizItem")
class QuizItemTest {

  @DisplayName("корректно создается билдером")
  @Test
  void shouldHaveBeenCorrectDoneByBuilder() {
    QuizItem quizItem = QuizItem.builder().question(TestHelper.QUESTION_1)
        .answers(TestHelper.ITEM_ANSWERS).build();
    assertAll(
        () -> assertThat(quizItem.getQuestion()).isEqualTo(TestHelper.QUESTION_1),
        () -> assertThat(quizItem.getAnswers())
            .contains(entry(TestHelper.ANSWER_1, TestHelper.IS_CORRECT_ANSWER),
                entry(TestHelper.ANSWER_2, TestHelper.IS_INCORRECT_ANSWER))
    );
  }

  @DisplayName("корректно создается билдером построчно")
  @Test
  void shouldHaveBeenCorrectDoneBySingularityBuilder() {
    QuizItem quizItem = QuizItem.builder()
        .question(TestHelper.QUESTION_1)
        .answer(TestHelper.ANSWER_1, TestHelper.IS_CORRECT_ANSWER)
        .answer(TestHelper.ANSWER_2, TestHelper.IS_INCORRECT_ANSWER)
        .build();
    assertAll(
        () -> assertThat(quizItem.getQuestion()).isEqualTo(TestHelper.QUESTION_1),
        () -> assertThat(quizItem.getAnswers())
            .contains(entry(TestHelper.ANSWER_1, TestHelper.IS_CORRECT_ANSWER),
                entry(TestHelper.ANSWER_2, TestHelper.IS_INCORRECT_ANSWER))
    );
  }

  @DisplayName("корректно создается конструктором")
  @Test
  void shouldHaveBeenCorrectDoneByConstructor() {
    QuizItem quizItem = new QuizItem(TestHelper.QUESTION_1, TestHelper.ITEM_ANSWERS);
    assertAll(
        () -> assertThat(quizItem.getQuestion()).isEqualTo(TestHelper.QUESTION_1),
        () -> assertThat(quizItem.getAnswers())
            .contains(entry(TestHelper.ANSWER_1, TestHelper.IS_CORRECT_ANSWER),
                entry(TestHelper.ANSWER_2, TestHelper.IS_INCORRECT_ANSWER))
    );
  }

}
