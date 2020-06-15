package ru.otus.homework.quiz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizProperties;
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
  private final Locale locale;
  private final MessageSource messageSource;

  private TestRoom testRoom;

  public QuizTestingServiceImpl(IOService ioService,
      QuizProperties quizProperties, MessageSource messageSource) {
    this.ioService = ioService;
    this.testQuestionsCount = quizProperties.getQuestionCount();
    this.passPercent = quizProperties.getPassPercent();
    this.locale = quizProperties.getLocale();
    this.messageSource = messageSource;
  }

  @Loggable
  @Override
  public void runTest(List<QuizQuestion> quizQuestions) {

    String firstName;
    String lastName;

    ioService.println(messageSource.getMessage("message.firstName", null, locale));
    firstName = ioService.readLine();
    ioService.println(messageSource.getMessage("message.lastName", null, locale));
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
    ioService.println(
        messageSource
            .getMessage("message.count",
                new String[]{String.valueOf(overallCount), String.valueOf(correctCount)}, locale));
    if (correctCount * 100 / overallCount >= passPercent) {
      ioService.println(messageSource.getMessage("message.congrats", null, locale));
    } else {
      ioService.println(messageSource.getMessage("message.sorry", null, locale));
    }
  }

}
