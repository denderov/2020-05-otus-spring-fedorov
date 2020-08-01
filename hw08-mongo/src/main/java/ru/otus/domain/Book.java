package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "BOOKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  private String id;

  @Field(name = "title")
  private String title;

  @Field(name = "author")
  private Author author;

  @Field(name = "genre")
  private Genre genre;

  public Book(String title, Author author, Genre genre) {
    this.title = title;
    this.author = author;
    this.genre = genre;
  }
}
