package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.atheneum.service.InteractiveService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

  private final InteractiveService interactiveService;

  @ShellMethod(value = "Print authors", key = {"a", "authors"})
  public String printAllAuthors() {
    interactiveService.printAllAuthors();
    return "End of author list";
  }

  @ShellMethod(value = "Print genres", key = {"g", "genres"})
  public String printAllGenres() {
    interactiveService.printAllGenres();
    return "End of genres list";
  }

  @ShellMethod(value = "Print books", key = {"b", "books"})
  public String printAllBooks() {
    interactiveService.printAllBooks();
    return "End of books list";
  }

  @ShellMethod(value = "Print comments", key = {"c", "comments"})
  public String printComments() {
    interactiveService.printComments();
    return "End of comment list";
  }

  @ShellMethod(value = "Save author", key = {"sa", "save author"})
  public String saveAuthor() {
    interactiveService.executeSaveAuthorProcess();
    return "Author saved";
  }

  @ShellMethod(value = "Save genre", key = {"sg", "save genre"})
  public String saveGenre() {
    interactiveService.executeSaveGenreProcess();
    return "Genre saved";
  }

  @ShellMethod(value = "Save book", key = {"sb", "save book"})
  public String saveBook() {
    interactiveService.executeSaveBookProcess();
    return "Book saved";
  }

  @ShellMethod(value = "Save comment", key = {"sc", "save comment"})
  public String saveComment() {
    interactiveService.executeSaveCommentProcess();
    return "Comment saved";
  }

  @ShellMethod(value = "Update book", key = {"ub", "update book"})
  public String updateBook() {
    interactiveService.executeUpdateBookProcess();
    return "Book updated";
  }

  @ShellMethod(value = "Update author", key = {"ua", "update author"})
  public String updateAuthor() {
    interactiveService.executeUpdateAuthorProcess();
    return "Author updated";
  }

  @ShellMethod(value = "Update genre", key = {"ug", "update genre"})
  public String updateGenre() {
    interactiveService.executeUpdateGenreProcess();
    return "Genre updated";
  }

  @ShellMethod(value = "Update comment", key = {"uc", "update comment"})
  public String updateComment() {
    interactiveService.executeUpdateCommentProcess();
    return "Comment updated";
  }

  @ShellMethod(value = "Delete book", key = {"db", "delete book"})
  public String deleteBook() {
    interactiveService.executeDeleteBookProcess();
    return "Book deleted";
  }

  @ShellMethod(value = "Delete author", key = {"da", "delete author"})
  public String deleteAuthor() {
    interactiveService.executeDeleteAuthorProcess();
    return "Author deleted";
  }

  @ShellMethod(value = "Delete genre", key = {"dg", "delete genre"})
  public String deleteGenre() {
    interactiveService.executeDeleteGenreProcess();
    return "Genre deleted";
  }

  @ShellMethod(value = "Delete comment", key = {"dc", "delete comment"})
  public String deleteComment() {
    interactiveService.executeDeleteCommentProcess();
    return "Comment deleted";
  }
}
