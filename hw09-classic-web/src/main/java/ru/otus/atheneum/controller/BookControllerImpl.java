package ru.otus.atheneum.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.service.BookService;
import ru.otus.domain.Book;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

  private final BookService bookService;
  private final ModelMapper modelMapper;

  @Override
  @GetMapping("/")
  public String bookList(Model model) {
    List<BookRow> books = bookService.getAll().stream().map(this::convertEntityToDto)
        .collect(Collectors.toList());
    log.info(books.toString());
    model.addAttribute("books", books);
    return "books";
  }

  @Override
  @GetMapping("/delete")
  public String deleteBook(Model model, @RequestParam("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удалена книга с id = %s", id));
    return "books";
  }

  private BookRow convertEntityToDto(Book book) {
    modelMapper.typeMap(Book.class, BookRow.class).addMappings(
        mapper -> {
          mapper.map(src -> src.getAuthor().getFullName(), BookRow::setAuthor);
          mapper.map(src -> src.getGenre().getName(), BookRow::setGenre);
        }
    );
    return modelMapper.map(book, BookRow.class);
  }
}
