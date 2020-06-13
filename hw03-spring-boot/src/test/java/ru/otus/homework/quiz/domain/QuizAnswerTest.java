package ru.otus.homework.quiz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс QuizAnswer")
class QuizAnswerTest {

  @DisplayName("корректно создается конструктором")
  @Test
  void shouldHaveBeenCorrectDoneByConstructor() {
    QuizAnswer quizAnswer = new QuizAnswer(TestHelper.ANSWER_1, TestHelper.IS_CORRECT_ANSWER);
    assertAll(
        () -> assertThat(quizAnswer.getAnswer()).isEqualTo(TestHelper.ANSWER_1),
        () -> assertThat(quizAnswer.isCorrect()).isEqualTo(TestHelper.IS_CORRECT_ANSWER)
    );
  }

}