package ru.otus.domain;

import lombok.Value;

@Value
public class Book {

  private long id;
  private String title;
  private Author author;
  private Genre genre;

}
