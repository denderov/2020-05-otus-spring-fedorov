package ru.otus.h2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(name = "author-n-genre",
        attributeNodes = {@NamedAttributeNode("h2Author"), @NamedAttributeNode("h2Genre")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class H2Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "EXT_ID")
  private String extId;

  @Column(name = "TITLE")
  private String title;

  @ManyToOne
  @JoinColumn(name = "AUTHOR")
  private H2Author h2Author;

  @ManyToOne
  @JoinColumn(name = "GENRE")
  private H2Genre h2Genre;
}
