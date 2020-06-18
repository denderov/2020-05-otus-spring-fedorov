package ru.otus.homework.quiz.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizProperties;
import ru.otus.homework.quiz.dao.QuizDao;

@DisplayName("Класс QuizServiceImpl")
@SpringBootTest
class QuizServiceImplTest {

  @MockBean
  private static IOService ioService;
  @Autowired
  private QuizProperties quizProperties;
  @MockBean
  private MessageSource messageSource;
  @MockBean
  private static QuizDao quizDao;

  @DisplayName("корректно печатает вопросы, полученные из DAO")
  @Test
  void shouldCorrectPrintQuestions() {

    given(quizDao.loadQuizItems()).willReturn(TestHelper.TEST_QUIZ_QUESTIONS);

    QuizTestingService quizTestingService = new QuizTestingServiceImpl(
        ioService, quizProperties, messageSource);
    QuizService quizService = new QuizServiceImpl(ioService, quizDao, quizTestingService);
    quizService.readQuiz();
    quizService.printQuizQuestions();

    verify(ioService, times(2)).println(anyString());
    verify(ioService, times(1)).println(TestHelper.QUESTION_1);
    verify(ioService, times(1)).println(TestHelper.QUESTION_2);

  }

}