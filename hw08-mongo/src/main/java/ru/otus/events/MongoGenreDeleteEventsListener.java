package ru.otus.events;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.atheneum.dao.BookRepository;
import ru.otus.atheneum.dao.GenreRepositoryException;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Component
@RequiredArgsConstructor

public class MongoGenreDeleteEventsListener extends AbstractMongoEventListener<Genre> {

    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        Document source = event.getSource();
        List<Book> booksByGenre = bookRepository.findByGenre_Id(source.get("_id").toString());
        if (booksByGenre.size() > 0) {
            throw new GenreRepositoryException("Нельзя удалить жанр у которого есть книги!");
        }
        super.onBeforeDelete(event);
    }
}
