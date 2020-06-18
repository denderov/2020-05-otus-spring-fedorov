package ru.otus.homework.quiz.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizTestProperties;
import ru.otus.homework.quiz.dao.QuizDao;

@DisplayName("Класс QuizServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

  private static final int DEFAULT_TEST_QUESTIONS_COUNT = 5;
  private static final int DEFAULT_PASS_PERCENT = 80;
  @Mock
  private static QuizDao quizDao;
  private static ByteArrayOutputStream testOut;
  @Mock
  private static IOService ioService;

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

    verify(ioService, times(2)).println(anyString());
    verify(ioService, times(1)).println(TestHelper.QUESTION_1);
    verify(ioService, times(1)).println(TestHelper.QUESTION_2);

  }

}