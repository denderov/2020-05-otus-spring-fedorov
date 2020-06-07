package ru.otus.homework.quiz.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizQuestion;
import ru.otus.homework.quiz.domain.TestQuestion;
import ru.otus.homework.quiz.domain.TestRoom;

@Service
@Getter
public class QuizServiceImpl implements QuizService {

  private final QuizDao quizDao;
  private final IOService ioService;
  private final int testQuestionsCount;
  private final int passPercent;

  private List<QuizQuestion> quizQuestions;
  private final InputStream in;
  private final PrintStream out;
  private TestRoom testRoom;

  public QuizServiceImpl(QuizDao quizDao, @Qualifier("ioService") IOService ioService,
      @Value("${test.questionCount}") int testQuestionsCount,
      @Value("${test.passPercent}") int passPercent) {
    this.quizDao = quizDao;
    this.ioService = ioService;
    this.testQuestionsCount = testQuestionsCount;
    this.passPercent = passPercent;
    in = this.ioService.in();
    out = this.ioService.out();
  }

  @Override
  public void readQuiz() {
    quizQuestions = quizDao.loadQuizItems();
  }

  @Override
  public void printQuizQuestions() {
    quizQuestions.forEach(quizQuestion -> ioService.out().println(quizQuestion.getQuestion()));
  }

  @Override
  public void runTest() {
    String firstName;
    String lastName;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      out.println("Enter yor first name:");
      firstName = br.readLine();
      out.println("Enter yor last name:");
      lastName = br.readLine();
      createTestRoom(firstName, lastName);
      processTest(br);
      evaluateResult();
    } catch (IOException original) {
      var e = new QuizTestingException("Ошибка ввода-вывода.");
      e.initCause(original);
      throw e;
    }
  }

  public void createTestRoom(String firstName, String lastName) {

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

  private void processTest(BufferedReader br) throws IOException {
    for (TestQuestion testQuestion :
        testRoom.getTestQuestions()
    ) {
      QuizQuestion quizQuestion = testQuestion.getQuizQuestion();
      out.println(formatQuestion(quizQuestion));
      String received = br.readLine();
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
    out.println(String
        .format("Overall count: %s. Correct count: %s.", overallCount, correctCount));
    if (correctCount * 100 / overallCount >= passPercent) {
      out.println("Congrats! You passed the test.");
    } else {
      out.println("Sorry. Try again.");
    }

  }
}
