package ru.otus.homework.quiz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс TestQuestion")
class TestQuestionTest {

  @DisplayName("корректно обрабатывает полученные ответы")
  @Test
  public void shouldCorrectHandleReceivedAnswers() {
    TestQuestion testQuestion = TestHelper.TEST_QUESTION_1;
    testQuestion.addReceivedAnswer(0).addReceivedAnswer(1);
    assertThat(testQuestion.getReceivedAnswers()).containsExactlyInAnyOrderElementsOf(Set.of(0, 1));
  }
}