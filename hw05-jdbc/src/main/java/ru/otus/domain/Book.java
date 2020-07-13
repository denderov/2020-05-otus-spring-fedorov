package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Book {

  private long id;
  private String title;
  private Author author;
  private Genre genre;

}
