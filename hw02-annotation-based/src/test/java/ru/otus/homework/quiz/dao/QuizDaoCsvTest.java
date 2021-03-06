package ru.otus.homework.quiz.dao;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;
import ru.otus.homework.quiz.domain.QuizQuestion;

@DisplayName("Класс QuizDaoImpl")
class QuizDaoCsvTest {

  @DisplayName("корректно читает вопросы из ридера в QuizItem")
  @Test
  void shouldCorrectReadQuiz() {

    String csvFileName = "correct_test.csv";

    QuizDao quizDao = new QuizDaoCsv(csvFileName);

    List<QuizQuestion> quizQuestionsFromDao = quizDao.loadQuizItems();

    assertThat(quizQuestionsFromDao).isEqualTo(TestHelper.TEST_QUIZ_QUESTIONS);

  }

  @DisplayName("корректно возвращает исключение при неправильном формате CSV")
  @Test
  void shouldCorrectThrowExceptionThenIncorrectCsvFormat() {

    QuizDao quizDao = new QuizDaoCsv("incorrect_test.csv");

    Throwable thrown = catchThrowable(quizDao::loadQuizItems);

    assertThat(thrown).isInstanceOf(QuizLoadingException.class)
        .hasMessageContaining("Проверка четности. "
            + "Строка должна содержать вопрос и пары ответ - "
            + "флаг корректности ответа, разделенные запятыми.");

  }

}
