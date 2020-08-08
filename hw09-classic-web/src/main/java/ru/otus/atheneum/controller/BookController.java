package ru.otus.atheneum.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

public interface BookController {

  @GetMapping("/")
  String bookList(Model model);

  @GetMapping("/book/delete/{id}")
  RedirectView deleteBook(Model model, @PathVariable("id") String id);

  @GetMapping("/book/edit/{id}")
  String editBook(Model model, @PathVariable("id") String id);
}
