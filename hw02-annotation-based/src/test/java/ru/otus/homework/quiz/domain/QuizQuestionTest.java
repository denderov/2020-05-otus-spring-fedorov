package ru.otus.homework.quiz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс QuizQuestion")
class QuizQuestionTest {

  @DisplayName("корректно создается конструктором")
  @Test
  void shouldHaveBeenCorrectDoneByConstructor() {
    QuizQuestion quizQuestion = new QuizQuestion(TestHelper.QUESTION_1, TestHelper.ANSWERS_1);
    assertAll(
        () -> assertThat(quizQuestion.getQuestion()).isEqualTo(TestHelper.QUESTION_1),
        () -> assertThat(quizQuestion.getAnswers()).isEqualTo(TestHelper.ANSWERS_1)
    );
  }

}