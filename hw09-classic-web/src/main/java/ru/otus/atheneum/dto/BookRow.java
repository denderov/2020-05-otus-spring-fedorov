package ru.otus.atheneum.dto;

import lombok.Data;

@Data
public class BookRow {

  private String id;
  private String title;
  private String authorId;
  private String author;
  private String genreId;
  private String genre;
}
