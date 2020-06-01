package ru.otus.homework.dao;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;
import ru.otus.homework.domain.QuizItem;

@DisplayName("Класс QuizDaoImpl")
class QuizDaoImplTest {

  @DisplayName("корректно читает вопросы из ридера в QuizItem")
  @Test
  void shouldCorrectReadQuiz() {

    String csvString = "Question1,Answer1,true,Answer2,false"
        + "\n"
        + "Question2,Answer1,0,Answer2,1";

    QuizDao quizDao = new QuizDaoImpl(new StringReader(csvString));

    List<QuizItem> quizItemsFromDao = quizDao.loadQuizItems();

    assertThat(quizItemsFromDao).isEqualTo(TestHelper.QUIZ_ITEMS_FROM_BUILDER);

  }

  @DisplayName("корректно возвращает исключение при неправильном формате CSV")
  @Test
  void shouldCorrectThrowExceptionThenIncorrectCsvFormat() {

    String csvString = "Question1,Answer1,true,Answer2,false"
        + "\n"
        + "Question2,Answer1,0,Answer2";

    QuizDao quizDao = new QuizDaoImpl(new StringReader(csvString));

    Throwable thrown = catchThrowable(quizDao::loadQuizItems);

    assertThat(thrown).isInstanceOf(QuizLoadingException.class)
        .hasMessageContaining("Проверка четности. "
            + "Строка должна содержать вопрос и пары ответ - "
            + "флаг корректности ответа, разделенные запятыми.");

  }

}
