package ru.otus.homework.quiz.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.quiz.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

  private final QuizService quizService;

  @ShellMethod(value = "Read quiz", key = {"r", "read"})
  public String readQuiz() {
    quizService.readQuiz();
    return "Quiz loaded";
  }

  @ShellMethod(value = "Run test", key = {"t", "test"})
  public String runTest() {
    quizService.runTest();
    return "Test completed";
  }
}
