package ru.otus.homework.quiz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.IOService;
import ru.otus.homework.common.IOServiceImpl;
import ru.otus.homework.config.QuizTestProperties;
import ru.otus.homework.quiz.dao.QuizDao;

@DisplayName("Класс QuizServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

  private static final int DEFAULT_TEST_QUESTIONS_COUNT = 5;
  private static final int DEFAULT_PASS_PERCENT = 80;
  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  @Mock
  private static QuizDao quizDao;
  private static ByteArrayOutputStream testOut;
  private static IOService ioService;

  @BeforeAll
  static void initIO() {
    testOut = new ByteArrayOutputStream();
    ioService = new IOServiceImpl(reader, new PrintStream(testOut));
  }

  @DisplayName("корректно печатает вопросы, полученные из DAO")
  @Test
  void shouldCorrectPrintQuestions() {

    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);

    QuizTestProperties quizTestProperties = new QuizTestProperties(DEFAULT_TEST_QUESTIONS_COUNT,
        DEFAULT_PASS_PERCENT);

    QuizTestingService quizTestingService = new QuizTestingServiceImpl(
        ioService, quizTestProperties);
    QuizService quizService = new QuizServiceImpl(ioService, quizDao, quizTestingService);
    quizService.readQuiz();
    quizService.printQuizQuestions();

    assertThat(testOut.toString()).contains(TestHelper.QUESTION_1
        + "\n"
        + TestHelper.QUESTION_2);
  }

}