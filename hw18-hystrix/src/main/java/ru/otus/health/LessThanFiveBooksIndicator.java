package ru.otus.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.atheneum.service.BookService;

@Component
@RequiredArgsConstructor
public class LessThanFiveBooksIndicator implements HealthIndicator {
    private final BookService bookService;

    @Override
    public Health health() {

        boolean LessThanFiveBooks = bookService.count() < 5;
        if (LessThanFiveBooks) {
            return Health.down()
                    .status(Status.DOWN)
                    .build();
        } else {
            return Health.up().build();
        }
    }
}