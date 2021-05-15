package ru.otus.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "GENRES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoGenre {

  @Id
  private String id;

  @Field(name = "name")
  private String name;

  public MongoGenre(String name) {
    this.name = name;
  }
}