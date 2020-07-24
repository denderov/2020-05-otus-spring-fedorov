package ru.otus.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "COMMENTS")
@Data
@ToString(exclude = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  @Id
  private String id;

  private LocalDateTime dateTime;

  private Book book;

  private String text;
}
