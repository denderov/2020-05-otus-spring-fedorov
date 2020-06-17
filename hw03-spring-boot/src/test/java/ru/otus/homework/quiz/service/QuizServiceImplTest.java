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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.IOService;
import ru.otus.homework.common.IOServiceImpl;
import ru.otus.homework.config.QuizProperties;
import ru.otus.homework.quiz.dao.QuizDao;

@DisplayName("Класс QuizServiceImpl")
@SpringBootTest
class QuizServiceImplTest {

  private static ByteArrayOutputStream testOut;

  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static IOService ioService;
  @Autowired
  private QuizProperties quizProperties;
  @Autowired
  private MessageSource messageSource;

  @MockBean
  private static QuizDao quizDao;

  @BeforeAll
  static void initIO() {
    testOut = new ByteArrayOutputStream();
    ioService = new IOServiceImpl(reader, new PrintStream(testOut));
  }

  @DisplayName("корректно печатает вопросы, полученные из DAO")
  @Test
  void shouldCorrectPrintQuestions() {

    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);

    QuizTestingService quizTestingService = new QuizTestingServiceImpl(
        ioService, quizProperties, messageSource);
    QuizService quizService = new QuizServiceImpl(ioService, quizDao, quizTestingService);
    quizService.readQuiz();
    quizService.printQuizQuestions();

    assertThat(testOut.toString()).contains(TestHelper.QUESTION_1
        + "\n"
        + TestHelper.QUESTION_2);
  }

}