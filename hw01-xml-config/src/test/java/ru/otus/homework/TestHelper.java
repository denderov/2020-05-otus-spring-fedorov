package ru.otus.homework;

import java.util.List;
import java.util.Map;
import ru.otus.homework.domain.QuizItem;

public class TestHelper {


  public static final String QUESTION_1 = "Question1";
  public static final String QUESTION_2 = "Question2";
  public static final String ANSWER_1 = "Answer1";
  public static final String ANSWER_2 = "Answer2";
  public static final boolean IS_CORRECT_ANSWER = true;
  public static final boolean IS_INCORRECT_ANSWER = false;
  public static final Map<String, Boolean> ANSWERS =
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
