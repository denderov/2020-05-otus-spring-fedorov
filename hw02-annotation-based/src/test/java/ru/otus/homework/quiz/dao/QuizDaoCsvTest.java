package ru.otus.homework.quiz.dao;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;
import ru.otus.homework.common.ConsoleService;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.domain.QuizQuestion;

@DisplayName("Класс QuizDaoImpl")
class QuizDaoCsvTest {

  private IOService getIoService(String csvString) {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(csvString.getBytes());
    return new ConsoleService(inputStream, System.out);
  }

  @DisplayName("корректно читает вопросы из ридера в QuizItem")
  @Test
  void shouldCorrectReadQuiz() {

    String csvString = "Question1,Answer1,true,Answer2,false"
        + "\n"
        + "Question2,Answer1,0,Answer2,1";

    IOService ioDaoService = getIoService(csvString);

    QuizDao quizDao = new QuizDaoCsv(ioDaoService);

    List<QuizQuestion> quizQuestionsFromDao = quizDao.loadQuizItems();

    assertThat(quizQuestionsFromDao).isEqualTo(TestHelper.TEST_QUIZ_QUESTIONS);

  }

  @DisplayName("корректно возвращает исключение при неправильном формате CSV")
  @Test
  void shouldCorrectThrowExceptionThenIncorrectCsvFormat() {

    String csvString = "Question1,Answer1,true,Answer2,false"
        + "\n"
        + "Question2,Answer1,0,Answer2";

    IOService ioDaoService = getIoService(csvString);

    QuizDao quizDao = new QuizDaoCsv(ioDaoService);

    Throwable thrown = catchThrowable(quizDao::loadQuizItems);

    assertThat(thrown).isInstanceOf(QuizLoadingException.class)
        .hasMessageContaining("Проверка четности. "
            + "Строка должна содержать вопрос и пары ответ - "
            + "флаг корректности ответа, разделенные запятыми.");

  }

}
