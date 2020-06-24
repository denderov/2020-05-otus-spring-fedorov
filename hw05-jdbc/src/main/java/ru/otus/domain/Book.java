package ru.otus.domain;

import lombok.Data;

@Data
public class Book {

  private long id;
  private String title;
  private Author author;
  private Genre genre;

}
