package ru.otus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(name = "author-n-genre",
    attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "TITLE")
  private String title;

  @ManyToOne
  @JoinColumn(name = "AUTHOR_ID")
  private Author author;

  @ManyToOne
  @JoinColumn(name = "GENRE_ID")
  private Genre genre;

}
