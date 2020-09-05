package ru.otus.atheneum.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.atheneum.dto.BookRow;
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
  public List<BookRow> showBookList(Model model) {
    List<BookRow> books = bookService.getAll().stream().map(entityConverter::convertBookEntityToDto)
        .collect(Collectors.toList());
    log.info(books.toString());
    return books;
  }

  @DeleteMapping("/api/book/{id}")
  public RedirectView deleteBookById(Model model, @PathVariable("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удаление книги с id = %s", id));
    return new RedirectView("/", true);
  }
//
//  @GetMapping("/book/edit/{id}")
//  public String showEditBookPage(Model model, @PathVariable("id") String id) {
//    Book book = bookService.getById(id)
//        .orElseThrow(
//            () -> new BookControllerException(
//                String.format("Нет книги по переданному идентификатору %s", id)));
//    BookRow bookRow = entityConverter.convertBookEntityToDto(book);
//    prepareModelForEditMapping(model, bookRow);
//    return "book_edit";
//  }
//
//  @GetMapping("/book/edit")
//  public String showCreateBookPage(Model model) {
//    BookRow bookRow = new BookRow();
//    prepareModelForEditMapping(model, bookRow);
//    return "book_edit";
//  }
//
//  @PostMapping("/book/edit")
//  public RedirectView savePerson(
//      BookRow bookRow,
//      Model model
//  ) {
//    log.info(bookRow.toString());
//    bookService.update(entityConverter.convertBookDtoToEntity(bookRow));
//    return new RedirectView("/", true);
//  }

//  private void prepareModelForEditMapping(Model model, BookRow bookRow) {
//    List<AuthorRow> authors = authorService.getAll().stream()
//        .map(entityConverter::convertAuthorEntityToDto)
//        .collect(Collectors.toList());
//    List<GenreRow> genres = genreService.getAll().stream()
//        .map(entityConverter::convertGenreEntityToDto)
//        .collect(Collectors.toList());
//    model.addAttribute("book", bookRow);
//    model.addAttribute("authors", authors);
//    model.addAttribute("genres", genres);
//  }

}
