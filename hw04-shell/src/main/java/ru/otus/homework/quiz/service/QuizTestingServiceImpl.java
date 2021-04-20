package ru.otus.homework.quiz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizProperties;
import ru.otus.homework.logging.Loggable;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;
import ru.otus.homework.quiz.domain.QuizSubject;
import ru.otus.homework.quiz.domain.TestQuestion;
import ru.otus.homework.quiz.domain.TestRoom;

@Service
public class QuizTestingServiceImpl implements QuizTestingService {

  private final IOService ioService;
  private final QuizResultService quizResultService;
  private final MessageSource messageSource;
  private final QuizProperties quizProperties;

  private TestRoom testRoom;
  private QuizSubject quizSubject;

  public QuizTestingServiceImpl(IOService ioService, QuizResultService quizResultService,
      QuizProperties quizProperties, MessageSource messageSource) {
    this.ioService = ioService;
    this.messageSource = messageSource;
    this.quizResultService = quizResultService;
    this.quizProperties = quizProperties;
  }

  @Loggable
  @Override
  public void runTest(List<QuizQuestion> quizQuestions) {

    createQuizSubject();
    createTestRoom(quizSubject, quizQuestions);
    processTest();
    evaluateResult();

  }

  private void createQuizSubject() {
    ioService.println(messageSource.getMessage("message.firstName", null, quizProperties.getLocale()));
    String firstName = ioService.readLine();
    ioService.println(messageSource.getMessage("message.lastName", null, quizProperties.getLocale()));
    String lastName = ioService.readLine();
    quizSubject = new QuizSubject(firstName, lastName);
  }

  @Loggable
  private void createTestRoom(QuizSubject quizSubject, List<QuizQuestion> quizQuestions) {

    List<TestQuestion> testQuestions = new Random()
        .ints(quizProperties.getQuestionCount() * 10, 0, quizQuestions.size())
        .distinct().limit(quizProperties.getQuestionCount())
        .mapToObj((i) -> new TestQuestion(quizQuestions.get(i)))
        .collect(Collectors.toList());

    testRoom = new TestRoom(quizSubject, testQuestions);
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
    quizResultService.evaluateResult(testRoom.getTestQuestions());
  }

}
