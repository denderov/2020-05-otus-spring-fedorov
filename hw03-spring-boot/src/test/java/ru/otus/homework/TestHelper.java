package ru.otus.homework;

import java.util.List;
import lombok.experimental.UtilityClass;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;
import ru.otus.homework.quiz.domain.TestQuestion;

@UtilityClass
public class TestHelper {

  public final String QUESTION_1 = "Question1";
  public final String QUESTION_2 = "Question2";
  public final String ANSWER_1 = "Answer1";
  public final String ANSWER_2 = "Answer2";
  public final boolean IS_CORRECT_ANSWER = true;
  public final boolean IS_INCORRECT_ANSWER = false;
  public final List<QuizAnswer> ANSWERS_1 = List.of(
      new QuizAnswer(ANSWER_1, IS_CORRECT_ANSWER),
      new QuizAnswer(ANSWER_2, IS_INCORRECT_ANSWER)
  );
  public final List<QuizAnswer> ANSWERS_2 = List.of(
      new QuizAnswer(ANSWER_1, IS_INCORRECT_ANSWER),
      new QuizAnswer(ANSWER_2, IS_CORRECT_ANSWER)
  );
  public final List<QuizQuestion> TEST_QUIZ_QUESTIONS =
      List.of(
          new QuizQuestion(QUESTION_1, ANSWERS_1),
          new QuizQuestion(QUESTION_2, ANSWERS_2)
      );

  public final TestQuestion TEST_QUESTION_1 = new TestQuestion(
      new QuizQuestion(QUESTION_1, ANSWERS_1));
  public final TestQuestion TEST_QUESTION_2 = new TestQuestion(
      new QuizQuestion(QUESTION_2, ANSWERS_2));
  public final List<TestQuestion> TEST_QUESTIONS = List.of(TEST_QUESTION_1, TEST_QUESTION_2);

  public final String TEST_MESSAGE = "Test message";

  public final String FIRST_NAME = "Vasily";
  public final String LAST_NAME = "Pupkin";

}
