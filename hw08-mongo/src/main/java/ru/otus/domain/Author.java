package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "AUTHORS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

  @Id
  private String id;

  @Field(name = "fullName")
  private String fullName;

  public Author(String fullName) {
    this.fullName = fullName;
  }

}
