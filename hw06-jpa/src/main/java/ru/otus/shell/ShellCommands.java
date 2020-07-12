package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.atheneum.service.AtheneumService;
import ru.otus.atheneum.service.InteractiveService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

  private final AtheneumService atheneumService;
  private final InteractiveService interactiveService;

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
    interactiveService.authorSaver();
    return "Author saved";
  }

  @ShellMethod(value = "Save genre", key = {"sg", "save genre"})
  public String saveGenre() {
    interactiveService.genreSaver();
    return "Genre saved";
  }

  @ShellMethod(value = "Save book", key = {"sb", "save book"})
  public String saveBook() {
    interactiveService.bookSaver();
    return "Book saved";
  }

  @ShellMethod(value = "Update book", key = {"ub", "update book"})
  public String updateBook() {
    interactiveService.bookUpdater();
    return "Book updated";
  }

  @ShellMethod(value = "Update author", key = {"ua", "update author"})
  public String updateAuthor() {
    interactiveService.authorUpdater();
    return "Author updated";
  }

  @ShellMethod(value = "Update genre", key = {"ug", "update genre"})
  public String updateGenre() {
    interactiveService.genreUpdater();
    return "Genre updated";
  }

  @ShellMethod(value = "Delete book", key = {"db", "delete book"})
  public String deleteBook() {
    interactiveService.bookDeleter();
    return "Book deleted";
  }

  @ShellMethod(value = "Delete author", key = {"da", "delete author"})
  public String deleteAuthor() {
    interactiveService.authorDeleter();
    return "Author deleted";
  }

  @ShellMethod(value = "Delete genre", key = {"dg", "delete genre"})
  public String deleteGenre() {
    interactiveService.genreDeleter();
    return "Genre deleted";
  }
}
