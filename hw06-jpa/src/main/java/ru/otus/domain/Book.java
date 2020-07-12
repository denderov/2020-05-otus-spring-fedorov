package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

  private long id;
  private String title;
  private Author author;
  private Genre genre;

}
