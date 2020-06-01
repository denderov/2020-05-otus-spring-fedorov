package ru.otus.homework;

import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizItem;
import ru.otus.homework.quiz.domain.QuizQuestion;

@UtilityClass
public class TestHelper {

  public static final String QUESTION_1 = "Question1";
  public static final String QUESTION_2 = "Question2";
  public static final String ANSWER_1 = "Answer1";
  public static final String ANSWER_2 = "Answer2";
  public static final boolean IS_CORRECT_ANSWER = true;
  public static final boolean IS_INCORRECT_ANSWER = false;
  public static final List<QuizAnswer> ANSWERS_1 = List.of(
      new QuizAnswer(ANSWER_1, IS_CORRECT_ANSWER),
      new QuizAnswer(ANSWER_2, IS_INCORRECT_ANSWER)
  );
  public static final List<QuizAnswer> ANSWERS_2 = List.of(
      new QuizAnswer(ANSWER_1, IS_INCORRECT_ANSWER),
      new QuizAnswer(ANSWER_2, IS_CORRECT_ANSWER)
  );
  public static final List<QuizQuestion> TEST_QUIZ_QUESTIONS =
      List.of(
          new QuizQuestion(QUESTION_1, ANSWERS_1),
          new QuizQuestion(QUESTION_2, ANSWERS_2)
      );

  public static final Map<String, Boolean> ITEM_ANSWERS =
      Map.of(ANSWER_1, IS_CORRECT_ANSWER, ANSWER_2, IS_INCORRECT_ANSWER);

  public static final List<QuizItem> QUIZ_ITEMS_FROM_BUILDER = List.of(
      QuizItem.builder()
          .question(QUESTION_1)
          .answers(Map.of(ANSWER_1, IS_CORRECT_ANSWER, ANSWER_2, IS_INCORRECT_ANSWER))
          .build(),
      QuizItem.builder()
          .question(QUESTION_2)
          .answers(Map.of(ANSWER_1, IS_INCORRECT_ANSWER, ANSWER_2, IS_CORRECT_ANSWER))
          .build()
  );

}
