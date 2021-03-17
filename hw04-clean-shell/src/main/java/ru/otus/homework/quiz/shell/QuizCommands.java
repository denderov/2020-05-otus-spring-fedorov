package ru.otus.homework.quiz.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.quiz.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

  private final QuizService quizService;

  @ShellMethod(value = "Load quiz (hint: try with parameter \"otus\")", key = {"l", "load"})
  public String readQuiz(@ShellOption(defaultValue = "") String quizName) {
    quizService.readQuiz(quizName);
    return "Quiz loaded";
  }

  @ShellMethod(value = "Run test", key = {"t", "test"})
  @ShellMethodAvailability("isRunTestAvailable")
  public String runTest() {
    quizService.runTest();
    return "Test completed";
  }

  private Availability isRunTestAvailable() {
    return quizService.isQuizQuestionsLoaded()?Availability.unavailable("First read quiz"): Availability.available();
  }
}
