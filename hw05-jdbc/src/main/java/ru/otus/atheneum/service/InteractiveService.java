package ru.otus.atheneum.service;

public interface InteractiveService {

  void executeSaveBookProcess();

  void executeSaveAuthorProcess();

  void executeSaveGenreProcess();

  void executeUpdateBookProcess();

  void executeUpdateAuthorProcess();

  void executeGenreBookProcess();

  void executeDeleteBookProcess();

  void executeDeleteAuthorProcess();

  void executeDeleteGenreProcess();

  void printAllAuthors();

  void printAllGenres();

  void printAllBooks();
}
