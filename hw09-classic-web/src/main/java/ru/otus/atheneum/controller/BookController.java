package ru.otus.atheneum.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookController {

  String bookList(Model model);

  @GetMapping("/delete")
  String deleteBook(Model model, @RequestParam("id") String id);
}
