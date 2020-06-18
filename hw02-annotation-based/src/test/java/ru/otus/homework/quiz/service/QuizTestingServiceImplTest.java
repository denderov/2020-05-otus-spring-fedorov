package ru.otus.homework.quiz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizTestProperties;
import ru.otus.homework.quiz.domain.TestQuestion;

@DisplayName("Класс QuizTestingServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuizTestingServiceImplTest {

  private static final int DEFAULT_TEST_QUESTIONS_COUNT = 5;
  private static final int DEFAULT_PASS_PERCENT = 80;
  @Mock
  private static IOService ioService;

  @DisplayName("корректно создает комнату для тестов")
  @Test
  void shouldCorrectCreateTestRoom() {
    QuizTestProperties quizTestProperties = new QuizTestProperties(DEFAULT_TEST_QUESTIONS_COUNT,
        DEFAULT_PASS_PERCENT);

    QuizTestingServiceImpl quizTestService = new QuizTestingServiceImpl(
        ioService, quizTestProperties);
    quizTestService.createTestRoom(TestHelper.FIRST_NAME, TestHelper.LAST_NAME,
        TestHelper.TEST_QUIZ_QUESTIONS);
    assertThat(
        quizTestService.getTestRoom().getTestQuestions().stream().map(TestQuestion::getQuizQuestion)
            .collect(Collectors.toList()))
        .containsExactlyInAnyOrderElementsOf(TestHelper.TEST_QUIZ_QUESTIONS);
  }

}