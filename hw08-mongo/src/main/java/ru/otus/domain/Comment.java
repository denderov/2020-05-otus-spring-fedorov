package ru.otus.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "COMMENTS")
@Data
@ToString(exclude = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  @Id
  private String id;

  @Field(name = "dateTime")
  private LocalDateTime dateTime;

  @Field(name = "book")
  private Book book;

  @Field(name = "text")
  private String text;

  public Comment(LocalDateTime dateTime, Book book, String text) {
    this.dateTime = dateTime;
    this.book = book;
    this.text = text;
  }
}
