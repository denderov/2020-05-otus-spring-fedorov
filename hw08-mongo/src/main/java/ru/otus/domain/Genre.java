package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GENRES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

  @Id
  private String id;

  private String name;

}
