package ru.otus.atheneum.service;

import java.util.Optional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface BookBuilder {

  String getTitle();

  BookBuilder setTitle(String title);

  Author getAuthor();

  BookBuilder setAuthor(Author author);

  Genre getGenre();

  BookBuilder setGenre(Genre genre);

  Optional<Book> createBook();
}
