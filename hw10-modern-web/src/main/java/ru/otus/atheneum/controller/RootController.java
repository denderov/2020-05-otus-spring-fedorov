package ru.otus.atheneum.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.atheneum.dto.AuthorRow;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.dto.GenreRow;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.atheneum.service.GenreService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RootController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final EntityConverter entityConverter;

  @GetMapping("/")
  public String showBookList(Model model) {
    return "books";
  }

  private void prepareModelForEditMapping(Model model, BookRow bookRow) {
    List<AuthorRow> authors = authorService.getAll().stream()
        .map(entityConverter::convertAuthorEntityToDto)
        .collect(Collectors.toList());
    List<GenreRow> genres = genreService.getAll().stream()
        .map(entityConverter::convertGenreEntityToDto)
        .collect(Collectors.toList());
    model.addAttribute("book", bookRow);
    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
  }

}
