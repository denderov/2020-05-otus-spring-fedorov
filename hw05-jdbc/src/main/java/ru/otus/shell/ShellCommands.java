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

  @ShellMethod(value = "Update book", key = {"ub", "update book"})
  public String updateBook() {
    atheneumService.interactiveBookUpdater();
    return "Book updated";
  }

  @ShellMethod(value = "Update author", key = {"ua", "update author"})
  public String updateAuthor() {
    atheneumService.interactiveAuthorUpdater();
    return "Author updated";
  }

  @ShellMethod(value = "Update genre", key = {"ug", "update genre"})
  public String updateGenre() {
    atheneumService.interactiveGenreUpdater();
    return "Genre updated";
  }
}
