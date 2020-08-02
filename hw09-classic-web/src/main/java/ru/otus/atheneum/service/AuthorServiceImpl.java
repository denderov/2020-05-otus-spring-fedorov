package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.AuthorRepository;
import ru.otus.domain.Author;

@Service
@RequiredArgsConstructor()
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Optional<Author> saveByNameAndGetAuthor(String fullName) {
    Author authorForSave = new Author();
    authorForSave.setFullName(fullName);
    return Optional.of(authorRepository.save(authorForSave));
  }

  @Override
  public void update(Author authorForUpdate) {
    authorRepository.save(authorForUpdate);
  }

  @Override
  public void delete(Author author) {
    authorRepository.delete(author);
  }

  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }
}
