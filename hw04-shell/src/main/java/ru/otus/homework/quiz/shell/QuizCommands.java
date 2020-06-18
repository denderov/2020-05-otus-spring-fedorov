package ru.otus.homework.quiz.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.quiz.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

  private final QuizService quizService;

  @ShellMethod(value = "Load quiz", key = {"l", "load"})
  public String readQuiz(@ShellOption(defaultValue = "") String quizName) {
    quizService.readQuiz(quizName);
    return "Quiz loaded";
  }

  @ShellMethod(value = "Run test", key = {"t", "test"})
  public String runTest() {
    quizService.runTest();
    return "Test completed";
  }
}
