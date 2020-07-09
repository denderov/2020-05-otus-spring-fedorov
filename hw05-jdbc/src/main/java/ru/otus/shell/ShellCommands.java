package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.atheneum.service.AtheneumService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

  private final AtheneumService atheneumService;

  @ShellMethod(value = "Print authors", key = {"a", "authors"})
  public String printAllAuthors() {
    atheneumService.printAllAuthors();
    return "End of author list";
  }

  @ShellMethod(value = "Print genres", key = {"g", "genres"})
  public String printAllGenres() {
    atheneumService.printAllGenres();
    return "End of genres list";
  }

  @ShellMethod(value = "Print books", key = {"b", "books"})
  public String printAllBooks() {
    atheneumService.printAllBooks();
    return "End of books list";
  }

  @ShellMethod(value = "Save author", key = {"sa", "save author"})
  public String saveAuthor() {
    atheneumService.interactiveAuthorSaver();
    return "Author saved";
  }

  @ShellMethod(value = "Save genre", key = {"sg", "save genre"})
  public String saveGenre() {
    atheneumService.interactiveGenreSaver();
    return "Genre saved";
  }

  @ShellMethod(value = "Save book", key = {"sb", "save book"})
  public String saveBook() {
    atheneumService.interactiveBookSaver();
    return "Book saved";
  }

//  private final QuizService quizService;
//
//  @ShellMethod(value = "Load quiz (hint: try with parameter \"otus\")", key = {"l", "load"})
//  public String readQuiz(@ShellOption(defaultValue = "") String quizName) {
//    quizService.readQuiz(quizName);
//    return "Quiz loaded";
//  }
//
//  @ShellMethod(value = "Run test", key = {"t", "test"})
//  public String runTest() {
//    quizService.runTest();
//    return "Test completed";
//  }
}
