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
import ru.otus.atheneum.dto.AuthorRow;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.dto.GenreRow;
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
  public List<BookRow> showBookList() {
    List<BookRow> books = bookService.getAll().stream().map(entityConverter::convertBookEntityToDto)
        .collect(Collectors.toList());
    log.info(books.toString());
    return books;
  }

  @GetMapping("/api/authors")
  public List<AuthorRow> showAuthors() {
    List<AuthorRow> authors = authorService.getAll().stream()
        .map(entityConverter::convertAuthorEntityToDto)
        .collect(Collectors.toList());
    log.info(authors.toString());
    return authors;
  }

  @GetMapping("/api/genres")
  public List<GenreRow> showGenres() {
    List<GenreRow> genres = genreService.getAll().stream()
        .map(entityConverter::convertGenreEntityToDto)
        .collect(Collectors.toList());
    log.info(genres.toString());
    return genres;
  }

  @GetMapping("/api/book/{id}")
  public BookRow showBookById(@PathVariable("id") String id) {
    BookRow book = entityConverter.convertBookEntityToDto(bookService.getById(id).orElseThrow());
    log.info(String.format("Возврат книги с id = %s", id));
    return book;
  }

  @DeleteMapping("/api/book/{id}")
  public String deleteBookById(@PathVariable("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удаление книги с id = %s", id));
    return "Book deleted";
  }

  @PostMapping("/api/book")
  public String saveBook(BookRow bookRow) {
    bookService.update(entityConverter.convertBookDtoToEntity(bookRow));
    log.info(String.format("Сохранение книги %s", bookRow));
    return "Book saved";
  }

}
