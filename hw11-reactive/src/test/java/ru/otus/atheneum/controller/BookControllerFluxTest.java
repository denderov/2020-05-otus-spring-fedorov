package ru.otus.atheneum.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.dao.BookReactiveRepository;
import ru.otus.atheneum.dto.BookDto;

@AutoConfigureDataMongo
@ComponentScan({"ru.otus.config", "ru.otus.atheneum.service"})
@RunWith(SpringRunner.class)
@WebFluxTest(BookControllerFlux.class)
@DisplayName("Класс BookControllerTest")
class BookControllerFluxTest {

  @Autowired
  WebTestClient webTestClient;

  @MockBean
  private BookReactiveRepository bookRepository;

  @Test
  @DisplayName("корректно отражает список книг")
  void shouldCorrectShowBooks() throws Exception {
    when(bookRepository.findAll()).thenReturn(Flux.fromIterable(TestHelper.BOOKS));
    webTestClient.get()
        .uri("/api/flux/books")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(BookDto.class)
        .hasSize(2)
        .contains(TestHelper.BOOK_DTO_1)
        .contains(TestHelper.BOOK_DTO_2);
  }
}