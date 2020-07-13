package ru.otus.atheneum.service;

import java.util.Optional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentService {

  Optional<Comment> saveAndGetComment(Book book, String text);

}
