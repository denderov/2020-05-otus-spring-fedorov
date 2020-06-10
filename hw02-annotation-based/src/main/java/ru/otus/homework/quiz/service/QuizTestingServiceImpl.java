package ru.otus.homework.quiz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizTestProperties;
import ru.otus.homework.logging.Loggable;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;
import ru.otus.homework.quiz.domain.TestQuestion;
import ru.otus.homework.quiz.domain.TestRoom;

@Service
@Getter
public class QuizTestingServiceImpl implements QuizTestingService {

  private final IOService ioService;
  private final int testQuestionsCount;
  private final int passPercent;

  private TestRoom testRoom;

  public QuizTestingServiceImpl(IOService ioService,
      QuizTestProperties quizTestProperties) {
    this.ioService = ioService;
    this.testQuestionsCount = quizTestProperties.getTestQuestionsCount();
    this.passPercent = quizTestProperties.getPassPercent();
  }

  @Loggable
  @Override
  public void runTest(List<QuizQuestion> quizQuestions) {

    String firstName;
    String lastName;

    ioService.println("Enter yor first name:");
    firstName = ioService.readLine();
    ioService.println("Enter yor last name:");
    lastName = ioService.readLine();
    createTestRoom(firstName, lastName, quizQuestions);
    processTest();
    evaluateResult();

  }

  @Loggable
  public void createTestRoom(String firstName, String lastName, List<QuizQuestion> quizQuestions) {

    List<TestQuestion> testQuestions = new Random()
        .ints(testQuestionsCount * 10, 0, quizQuestions.size())
        .distinct().limit(testQuestionsCount)
        .mapToObj((i) -> new TestQuestion(quizQuestions.get(i)))
        .collect(Collectors.toList());

    testRoom = new TestRoom(firstName, lastName, testQuestions);
  }

  private String formatQuestion(QuizQuestion quizQuestion) {

    List<QuizAnswer> quizAnswers = quizQuestion.getAnswers();
    String formattedAnswers = IntStream.range(0, quizAnswers.size())
        .mapToObj((i) -> String.format("%d. %s", i + 1, quizAnswers.get(i).getAnswer()))
        .collect(Collectors.joining("\n"));

    return String.format("%s\n%s", quizQuestion.getQuestion(), formattedAnswers);
  }

  private void processTest() {
    for (TestQuestion testQuestion :
        testRoom.getTestQuestions()
    ) {
      QuizQuestion quizQuestion = testQuestion.getQuizQuestion();
      ioService.println(formatQuestion(quizQuestion));
      String received = ioService.readLine();
      var receivedAnswers = received.split("\\D+");
      Arrays.stream(receivedAnswers).map(Integer::valueOf)
          .forEach(testQuestion::addReceivedAnswer);
    }
  }

  private void evaluateResult() {
    int overallCount = testRoom.getTestQuestions().size();
    int correctCount = 0;
    for (TestQuestion testQuestion :
        testRoom.getTestQuestions()
    ) {
      List<QuizAnswer> quizAnswers = testQuestion.getQuizQuestion().getAnswers();
      long incorrectCount = IntStream.range(0, quizAnswers.size())
          .filter(
              (i) -> quizAnswers.get(i).isCorrect()
                  ^ testQuestion.getReceivedAnswers().contains(i + 1))
          .count();
      if (incorrectCount == 0) {
        correctCount++;
      }
    }
    ioService.println(String
        .format("Overall count: %s. Correct count: %s.", overallCount, correctCount));
    if (correctCount * 100 / overallCount >= passPercent) {
      ioService.println("Congrats! You passed the test.");
    } else {
      ioService.println("Sorry. Try again.");
    }
  }

}
