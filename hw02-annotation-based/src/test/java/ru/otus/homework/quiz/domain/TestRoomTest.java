package ru.otus.homework.quiz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс QuizQuestion")
class TestRoomTest {

  @DisplayName("корректно создается конструктором")
  @Test
  void shouldHaveBeenCorrectDoneByConstructor() {
    TestRoom testRoom = new TestRoom(TestHelper.FIRST_NAME, TestHelper.LAST_NAME,
        TestHelper.TEST_QUESTIONS);
    assertAll(
        () -> assertThat(testRoom.getFirstName()).isEqualTo(TestHelper.FIRST_NAME),
        () -> assertThat(testRoom.getLastName()).isEqualTo(TestHelper.LAST_NAME),
        () -> assertThat(testRoom.getTestQuestions())
            .containsExactlyInAnyOrderElementsOf(TestHelper.TEST_QUESTIONS)
    );
  }

}