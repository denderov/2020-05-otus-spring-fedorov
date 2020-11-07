package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

  @Id
  private String id;

  @Field(name = "username")
  private String username;
  @Field(name = "password")
  //:)
  private String password;
  @Field(name = "role")
  private String role;

  public Users(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
