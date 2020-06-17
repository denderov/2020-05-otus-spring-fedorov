package ru.otus.homework.quiz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizProperties;
import ru.otus.homework.quiz.domain.TestQuestion;

@DisplayName("Класс QuizTestingServiceImpl")
@SpringBootTest
class QuizTestingServiceImplTest {

  @Autowired
  private QuizProperties quizProperties;
  @Autowired
  private MessageSource messageSource;

  @MockBean
  private static IOService ioService;

  @DisplayName("корректно создает комнату для тестов")
  @Test
  void shouldCorrectCreateTestRoom() {

    QuizTestingServiceImpl quizTestService = new QuizTestingServiceImpl(
        ioService, quizProperties, messageSource);
    quizTestService.createTestRoom(TestHelper.FIRST_NAME, TestHelper.LAST_NAME,
        TestHelper.TEST_QUIZ_QUESTIONS);
    assertThat(
        quizTestService.getTestRoom().getTestQuestions().stream().map(TestQuestion::getQuizQuestion)
            .collect(Collectors.toList()))
        .containsExactlyInAnyOrderElementsOf(TestHelper.TEST_QUIZ_QUESTIONS);
  }

}