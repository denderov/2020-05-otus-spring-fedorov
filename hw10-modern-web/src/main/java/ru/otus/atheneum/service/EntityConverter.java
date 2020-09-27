package ru.otus.atheneum.service;

import ru.otus.atheneum.dto.AuthorDto;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.dto.GenreDto;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface EntityConverter {

  BookDto convertBookEntityToDto(Book book);

  Book convertBookDtoToEntity(BookDto bookDto);

  AuthorDto convertAuthorEntityToDto(Author author);

  GenreDto convertGenreEntityToDto(Genre genre);
}
