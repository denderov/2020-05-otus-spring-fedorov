package ru.otus.atheneum.service;

import java.util.List;
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

  List<BookDto> convertBookEntitiesToDto(List<Book> books);

  List<Book> convertBooksDtoToEntities(List<BookDto> booksDto);

  List<AuthorDto> convertAuthorEntitiesToDto(List<Author> authors);

  List<GenreDto> convertGenreEntitiesToDto(List<Genre> genres);
}
