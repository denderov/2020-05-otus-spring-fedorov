package ru.otus.mongo.domain;

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
public class MongoBook {

  @Id
  private String id;

  @Field(name = "title")
  private String title;

  @Field(name = "author")
  private MongoAuthor mongoAuthor;

  @Field(name = "genre")
  private MongoGenre mongoGenre;

  public MongoBook(String title, MongoAuthor mongoAuthor, MongoGenre mongoGenre) {
    this.title = title;
    this.mongoAuthor = mongoAuthor;
    this.mongoGenre = mongoGenre;
  }
}
