package ru.otus.homework.quiz.dao;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.TestHelper;
import ru.otus.homework.quiz.domain.QuizQuestion;

@DisplayName("Класс QuizDaoImpl")
@SpringBootTest
class QuizDaoCsvTest {

  @Autowired
  QuizDao correctQuizDao;
  @Autowired
  QuizDao incorrectQuizDao;

  @DisplayName("корректно читает вопросы из ридера в QuizItem")
  @Test
  void shouldCorrectReadQuiz() {

    List<QuizQuestion> quizQuestionsFromDao = correctQuizDao.loadQuizItems();

    assertThat(quizQuestionsFromDao).isEqualTo(TestHelper.TEST_QUIZ_QUESTIONS);

  }

  @DisplayName("корректно возвращает исключение при неправильном формате CSV")
  @Test
  void shouldCorrectThrowExceptionThenIncorrectCsvFormat() {

    Throwable thrown = catchThrowable(incorrectQuizDao::loadQuizItems);

    assertThat(thrown).isInstanceOf(QuizLoadingException.class)
        .hasMessageContaining("Проверка четности. "
            + "Строка должна содержать вопрос и пары ответ - "
            + "флаг корректности ответа, разделенные запятыми.");

  }

  @Configuration
  public static class TestConfig {

    @Bean
    QuizDao correctQuizDao() {
      return new QuizDaoCsv("correct_test", Locale.US);
    }

    @Bean
    QuizDao incorrectQuizDao() {
      return new QuizDaoCsv("incorrect_test", Locale.US);
    }
  }

}
