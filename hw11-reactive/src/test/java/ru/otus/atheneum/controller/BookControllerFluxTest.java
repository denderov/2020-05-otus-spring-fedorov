package ru.otus.atheneum.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.atheneum.TestHelper;
import ru.otus.atheneum.dao.AuthorReactiveRepository;
import ru.otus.atheneum.dao.BookReactiveRepository;
import ru.otus.atheneum.dao.GenreReactiveRepository;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.service.EntityConverter;

//@ComponentScan(excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
//    MongockConfiguration.class, MongockContext.class}))
//@Configuration
//@ComponentScan(excludeFilters = {
////    @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
////        MongockConfiguration.class, MongockContext.class}),
//    @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
//    @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
//})
@WebFluxTest(BookControllerFlux.class)
@DisplayName("Класс BookControllerTest")
class BookControllerFluxTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private EntityConverter entityConverter;

  @MockBean
  private AuthorReactiveRepository authorRepository;

  @MockBean
  private BookReactiveRepository bookRepository;

  @MockBean
  private GenreReactiveRepository genreRepository;

  @Test
  @DisplayName("корректно отражает список книг")
  void shouldCorrectShowBooks() throws Exception {
    when(bookRepository.findAll()).thenReturn(Flux.fromIterable(TestHelper.BOOKS));
    when(entityConverter.convertBookEntitiesToDto(any(Flux.class)))
        .thenReturn(Flux.fromIterable(TestHelper.BOOK_DTOS));
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

  //Подменяем конфигурацию, чтобы не тащить @EnableMongock
  @Configuration
  @ComponentScan(excludeFilters = {
      @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class)})
  static class Config {

  }

}