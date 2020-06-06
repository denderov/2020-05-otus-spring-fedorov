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
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.TestQuestion;

@DisplayName("Класс QuizService")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

  private static final int DEFAULT_TEST_QUESTIONS_COUNT = 5;
  private static final int DEFAULT_PASS_PERCENT = 80;
  @Mock
  private static QuizDao quizDao;
  private static ByteArrayOutputStream testOut;
  private static IOService IOService;

  @BeforeAll
  static void initIO() {
    testOut = new ByteArrayOutputStream();
    IOService = new ConsoleService(System.in, new PrintStream(testOut));
  }

  @DisplayName("корректно печатает вопросы, полученные из DAO")
  @Test
  void shouldCorrectPrintQuestions() {

    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);

    QuizService quizService = new QuizServiceImpl(quizDao, IOService, DEFAULT_TEST_QUESTIONS_COUNT,
        DEFAULT_PASS_PERCENT);
    quizService.readQuiz();
    quizService.printQuizQuestions();

    assertThat(testOut.toString()).contains(TestHelper.QUESTION_1
        + "\n"
        + TestHelper.QUESTION_2);
  }

  @DisplayName("корректно создает комнату для тестов")
  @Test
  void shouldCorrectCreateTestRoom() {
    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);
    QuizServiceImpl quizService = new QuizServiceImpl(quizDao, IOService,
        DEFAULT_TEST_QUESTIONS_COUNT, DEFAULT_PASS_PERCENT);
    quizService.readQuiz();
    quizService.createTestRoom(TestHelper.FIRST_NAME, TestHelper.LAST_NAME);
    assertThat(
        quizService.getTestRoom().getTestQuestions().stream().map(TestQuestion::getQuizQuestion)
            .collect(Collectors.toList()))
        .containsExactlyInAnyOrderElementsOf(TestHelper.TEST_QUIZ_QUESTIONS);
  }
}