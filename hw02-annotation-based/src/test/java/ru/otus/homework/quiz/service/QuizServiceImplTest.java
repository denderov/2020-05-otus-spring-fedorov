package ru.otus.homework.quiz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.ConsoleService;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.dao.QuizDao;

@DisplayName("Класс QuizService")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

  @Mock
  private QuizDao quizDao;

  private ByteArrayOutputStream testOut;
  private IOService IOService;

  @DisplayName("корректно печатает вопросы, полученные из DAO")
  @Test
  void shouldCorrectPrintQuestions() {

    testOut = new ByteArrayOutputStream();
    IOService = new ConsoleService(System.in, new PrintStream(testOut));

    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);

    QuizService quizService = new QuizServiceImpl(quizDao, IOService);
    quizService.readQuiz();
    quizService.printQuizQuestions();

    assertThat(testOut.toString()).contains(TestHelper.QUESTION_1
        + "\n"
        + TestHelper.QUESTION_2);
  }
}