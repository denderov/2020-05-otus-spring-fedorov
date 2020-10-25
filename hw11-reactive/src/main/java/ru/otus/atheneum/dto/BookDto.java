package ru.otus.atheneum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

  private String id;
  private String title;
  private String authorId;
  private String author;
  private String genreId;
  private String genre;
}
