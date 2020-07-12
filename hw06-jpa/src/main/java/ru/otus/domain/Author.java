package ru.otus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AUTHORS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

  @Id
  @Column(name = "ID")
  private long id;

  @Column(name = "FULL_NAME")
  private String fullName;

}
