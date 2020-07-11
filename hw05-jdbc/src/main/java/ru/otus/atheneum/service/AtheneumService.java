package ru.otus.atheneum.service;

import ru.otus.domain.Author;
import ru.otus.domain.Genre;

public interface AtheneumService {

  void printAllBooks();

  void printAllAuthors();

  void printAllGenres();

  void saveBook();

  void setBookTitle(String bookTitle);

  void setBookAuthor(Author author);

  void setBookGenre(Genre genre);

  void setBookAuthorByPosition(int authorPosition);

  void setBookGenreByPosition(int genrePosition);

  void saveAuthorByName(String authorFullName);

  void saveGenreByName(String genreName);

  void interactiveBookSaver();

  void interactiveAuthorSaver();

  void interactiveGenreSaver();

  void interactiveBookUpdater();

  void interactiveAuthorUpdater();

  void interactiveGenreUpdater();
}
