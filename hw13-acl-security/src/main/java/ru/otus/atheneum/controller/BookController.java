package ru.otus.atheneum.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.atheneum.dto.AuthorDto;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.dto.GenreDto;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.atheneum.service.GenreService;
import ru.otus.domain.Book;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final EntityConverter entityConverter;

  @GetMapping("/")
  public String showBookList(Model model) {
    List<BookDto> books = entityConverter.convertBookEntitiesToDto(bookService.getAll());
    log.info(books.toString());
    model.addAttribute("books", books);
    return "books";
  }

  @DeleteMapping("/book/{id}")
  public RedirectView deleteBookById(Model model, @PathVariable("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удаление книги с id = %s", id));
    return new RedirectView("/", true);
  }

  @GetMapping("/book/edit/{id}")
  public String showEditBookPage(Model model, @PathVariable("id") String id) {
    Book book = bookService.getById(id)
        .orElseThrow(
            () -> new BookControllerException(
                String.format("Нет книги по переданному идентификатору %s", id)));
    BookDto bookDto = entityConverter.convertBookEntityToDto(book);
    prepareModelForEditMapping(model, bookDto);
    return "book_edit";
  }

  @GetMapping("/book/edit")
  public String showCreateBookPage(Model model) {
    BookDto bookDto = new BookDto();
    prepareModelForEditMapping(model, bookDto);
    return "book_edit";
  }

  @PostMapping("/book/edit")
  public RedirectView savePerson(
      BookDto bookDto,
      Model model
  ) {
    log.info(bookDto.toString());
    bookService.update(entityConverter.convertBookDtoToEntity(bookDto));
    return new RedirectView("/", true);
  }

  private void prepareModelForEditMapping(Model model, BookDto bookDto) {
    List<AuthorDto> authors = entityConverter.convertAuthorEntitiesToDto(authorService.getAll());
    List<GenreDto> genres = entityConverter.convertGenreEntitiesToDto(genreService.getAll());
    model.addAttribute("book", bookDto);
    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
  }

}
