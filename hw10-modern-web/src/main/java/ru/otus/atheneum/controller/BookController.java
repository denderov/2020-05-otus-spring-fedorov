package ru.otus.atheneum.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.atheneum.dto.AuthorDto;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.dto.GenreDto;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.atheneum.service.GenreService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final EntityConverter entityConverter;

  @GetMapping("/api/books")
  public List<BookDto> showBookList() {
    List<BookDto> books = bookService.getAll().stream().map(entityConverter::convertBookEntityToDto)
        .collect(Collectors.toList());
    log.info(books.toString());
    return books;
  }

  @GetMapping("/api/authors")
  public List<AuthorDto> showAuthors() {
    List<AuthorDto> authors = authorService.getAll().stream()
        .map(entityConverter::convertAuthorEntityToDto)
        .collect(Collectors.toList());
    log.info(authors.toString());
    return authors;
  }

  @GetMapping("/api/genres")
  public List<GenreDto> showGenres() {
    List<GenreDto> genres = genreService.getAll().stream()
        .map(entityConverter::convertGenreEntityToDto)
        .collect(Collectors.toList());
    log.info(genres.toString());
    return genres;
  }

  @GetMapping("/api/books/{id}")
  public BookDto showBookById(@PathVariable("id") String id) {
    BookDto book = entityConverter.convertBookEntityToDto(bookService.getById(id).orElseThrow());
    log.info(String.format("Возврат книги с id = %s", id));
    return book;
  }

  @DeleteMapping("/api/books/{id}")
  public String deleteBookById(@PathVariable("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удаление книги с id = %s", id));
    return "Book deleted";
  }

  @PostMapping("/api/books")
  public String saveBook(BookDto bookDto) {
    bookService.update(entityConverter.convertBookDtoToEntity(bookDto));
    log.info(String.format("Сохранение книги %s", bookDto));
    return "Book saved";
  }

}
