package ru.otus.atheneum.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BookController {

  @GetMapping("/")
  String bookList(Model model);

  @GetMapping("/book/delete/{id}")
  String deleteBook(Model model, @PathVariable("id") String id);
}
