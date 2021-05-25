package ru.otus.atheneum.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.AuthorRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

@Service
@RequiredArgsConstructor()
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyAuthor")
  public Optional<Author> saveByNameAndGetAuthor(String fullName) {
    Author authorForSave = new Author();
    authorForSave.setFullName(fullName);
    return Optional.of(authorRepository.save(authorForSave));
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void update(Author authorForUpdate) {
    authorRepository.save(authorForUpdate);
  }

  @Override
  @HystrixCommand(fallbackMethod = "emptyFallback")
  public void delete(Author author) {
    authorRepository.delete(author);
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyAuthorList")
  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  @Override
  @HystrixCommand(fallbackMethod = "returnEmptyAuthor")
  public Optional<Author> getById(String id) {
    return authorRepository.findById(id);
  }

  public List<Author> returnEmptyAuthorList() {
    return Collections.emptyList();
  }

  public Optional<Author> returnEmptyAuthor(String id) {
    return Optional.empty();
  }

  public void emptyFallback(Author author) {

  }
}
