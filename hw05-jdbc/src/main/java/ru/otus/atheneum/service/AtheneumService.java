package ru.otus.atheneum.service;

import java.util.Optional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
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

  void initBook();

  Optional<Book> getBookFromPreparedListByPosition(int bookPosition);

  Optional<Author> getAuthorFromPreparedListByPosition(int authorPosition);

  Optional<Genre> getGenreFromPreparedListByPosition(int genrePosition);

  void updateBook(Book book);

  void updateAuthor(Author author);

  void updateGenre(Genre genre);

  void deleteAuthor(Author author);

  void deleteGenre(Genre genre);

  void deleteBook(Book book);
}
