package ru.otus.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import ru.otus.atheneum.dao.AuthorRepository;
import ru.otus.atheneum.dao.BookRepository;
import ru.otus.atheneum.dao.GenreRepository;
import ru.otus.domain.Book;

@Component
@RequiredArgsConstructor
public class MongoBookSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Book> event) {
        super.onBeforeSave(event);
        var book = event.getSource();
        authorRepository.save(book.getAuthor());
        genreRepository.save(book.getGenre());
    }
}
