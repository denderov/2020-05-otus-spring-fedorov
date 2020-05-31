package ru.otus.homework.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.TestHelper;
import ru.otus.homework.dao.QuizDao;

@DisplayName("Класс QuizService")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

  private final PrintStream systemOut = System.out;
  @Mock
  private QuizDao quizDao;
  private ByteArrayOutputStream testOut;

  @BeforeEach
  void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  @AfterEach
  void restoreOutput() {
    System.setOut(systemOut);
  }

  @DisplayName("корректно печатает вопросы, полученные из DAO")
  @Test
  void shouldCorrectPrintQuestions() {

    given(quizDao.processQuizItemsFromDefaultCsv()).willReturn(TestHelper.QUIZ_ITEMS_FROM_BUILDER);

    QuizService quizService = new QuizServiceImpl(quizDao);
    quizService.readQuiz();
    quizService.printQuizQuestions();

    assertThat(testOut.toString()).contains(TestHelper.QUESTION_1
        + "\n"
        + TestHelper.QUESTION_2);
  }
}