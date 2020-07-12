package ru.otus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GENRES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

  @Id
  @Column(name = "ID")
  private long id;

  @Column(name = "NAME")
  private String name;

}
