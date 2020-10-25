package ru.otus.atheneum.service;

public interface InteractiveService {

  void executeSaveBookProcess();

  void executeSaveAuthorProcess();

  void executeSaveGenreProcess();

  void executeSaveCommentProcess();

  void executeUpdateBookProcess();

  void executeUpdateAuthorProcess();

  void executeUpdateGenreProcess();

  void executeUpdateCommentProcess();

  void executeDeleteBookProcess();

  void executeDeleteAuthorProcess();

  void executeDeleteGenreProcess();

  void executeDeleteCommentProcess();

  void printAllAuthors();

  void printAllGenres();

  void printAllBooks();

  void printComments();
}
