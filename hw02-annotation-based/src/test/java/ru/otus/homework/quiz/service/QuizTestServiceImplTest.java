package ru.otus.homework.quiz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.ConsoleService;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.TestQuestion;

@DisplayName("Класс QuizTestServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuizTestServiceImplTest {

  private static final int DEFAULT_TEST_QUESTIONS_COUNT = 5;
  private static final int DEFAULT_PASS_PERCENT = 80;
  @Mock
  private static QuizDao quizDao;
  private static ByteArrayOutputStream testOut;
  private static ru.otus.homework.common.IOService IOService;

  @BeforeAll
  static void initIO() {
    testOut = new ByteArrayOutputStream();
    IOService = new ConsoleService(System.in, new PrintStream(testOut));
  }

  @DisplayName("корректно создает комнату для тестов")
  @Test
  void shouldCorrectCreateTestRoom() {
    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);
    QuizTestServiceImpl quizTestService = new QuizTestServiceImpl(IOService,
        DEFAULT_TEST_QUESTIONS_COUNT, DEFAULT_PASS_PERCENT);
    QuizServiceImpl quizService = new QuizServiceImpl(IOService, quizDao, quizTestService);
    quizService.readQuiz();
    quizTestService.createTestRoom(TestHelper.FIRST_NAME, TestHelper.LAST_NAME,
        quizService.getQuizQuestions());
    assertThat(
        quizTestService.getTestRoom().getTestQuestions().stream().map(TestQuestion::getQuizQuestion)
            .collect(Collectors.toList()))
        .containsExactlyInAnyOrderElementsOf(TestHelper.TEST_QUIZ_QUESTIONS);
  }

}