package ru.otus.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.h2.domain.H2Author;
import ru.otus.h2.domain.H2Book;
import ru.otus.h2.domain.H2Genre;
import ru.otus.h2.repository.H2AuthorRepository;
import ru.otus.h2.repository.H2BookRepository;
import ru.otus.h2.repository.H2GenreRepository;
import ru.otus.mongo.domain.MongoAuthor;
import ru.otus.mongo.domain.MongoBook;
import ru.otus.mongo.domain.MongoGenre;
import ru.otus.mongo.repository.MongoAuthorRepository;
import ru.otus.mongo.repository.MongoGenreRepository;

import java.util.HashMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfig {

  private final MongoTemplate mongoTemplate;
  private final StepBuilderFactory stepBuilderFactory;
  private final H2AuthorRepository h2AuthorRepository;
  private final H2BookRepository h2BookRepository;
  private final H2GenreRepository h2GenreRepository;
  private final JobBuilderFactory jobBuilderFactory;
  private static final int CHUNK_SIZE = 5;

  @StepScope
  @Bean
  public MongoItemReader<MongoAuthor> authorMongoItemReader() {
    return new MongoItemReaderBuilder<MongoAuthor>()
            .name("authorMongoItemReader")
            .template(mongoTemplate)
            .targetType(MongoAuthor.class)
            .jsonQuery("{ }")
            .sorts(new HashMap<>())
            .build();
  }

  @StepScope
  @Bean
  public ItemProcessor<MongoAuthor, H2Author> authorItemProcessor() {
    return (mongoAuthor) -> {
      H2Author h2Author = new H2Author();
      h2Author.setExtId(mongoAuthor.getId());
      h2Author.setFullName(mongoAuthor.getFullName());
      return h2Author;
    };
  }

  @StepScope
  @Bean
  public ItemWriter<H2Author> authorItemWriter() {
    RepositoryItemWriter<H2Author> writer = new RepositoryItemWriter<>();
    writer.setMethodName("save");
    writer.setRepository(h2AuthorRepository);
    return writer;
  }

  @Bean
  public Step authorStep() {
    return stepBuilderFactory.get("authorStep")
            .<MongoAuthor, H2Author>chunk(CHUNK_SIZE)
            .reader(authorMongoItemReader())
            .processor(authorItemProcessor())
            .writer(authorItemWriter())
            .allowStartIfComplete(true)
            .build();
  }

  @StepScope
  @Bean
  public MongoItemReader<MongoGenre> genreMongoItemReader() {
    return new MongoItemReaderBuilder<MongoGenre>()
            .name("genreMongoItemReader")
            .template(mongoTemplate)
            .targetType(MongoGenre.class)
            .jsonQuery("{ }")
            .sorts(new HashMap<>())
            .build();
  }

  @StepScope
  @Bean
  public ItemProcessor<MongoGenre, H2Genre> genreItemProcessor() {
    return (mongoGenre) -> {
      H2Genre h2Genre = new H2Genre();
      h2Genre.setExtId(mongoGenre.getId());
      h2Genre.setName(mongoGenre.getName());
      return h2Genre;
    };
  }

  @StepScope
  @Bean
  public ItemWriter<H2Genre> genreItemWriter() {
    RepositoryItemWriter<H2Genre> writer = new RepositoryItemWriter<>();
    writer.setMethodName("save");
    writer.setRepository(h2GenreRepository);
    return writer;
  }

  @Bean
  public Step genreStep() {
    return stepBuilderFactory.get("genreStep")
            .<MongoGenre, H2Genre>chunk(CHUNK_SIZE)
            .reader(genreMongoItemReader())
            .processor(genreItemProcessor())
            .writer(genreItemWriter())
            .allowStartIfComplete(true)
            .build();
  }


  @StepScope
  @Bean
  public MongoItemReader<MongoBook> bookMongoItemReader() {
    return new MongoItemReaderBuilder<MongoBook>()
            .name("bookMongoItemReader")
            .template(mongoTemplate)
            .targetType(MongoBook.class)
            .jsonQuery("{ }")
            .sorts(new HashMap<>())
            .build();
  }

  @StepScope
  @Bean
  public ItemProcessor<MongoBook, H2Book> bookItemProcessor() {
    return (mongoBook) -> {
      H2Book h2Book = new H2Book();
      h2Book.setExtId(mongoBook.getId());
      h2Book.setTitle(mongoBook.getTitle());
      h2Book.setH2Author(h2AuthorRepository.findByExtId(mongoBook.getMongoAuthor().getId()));
      h2Book.setH2Genre(h2GenreRepository.findByExtId(mongoBook.getMongoGenre().getId()));
      return h2Book;
    };
  }

  @StepScope
  @Bean
  public ItemWriter<H2Book> bookItemWriter() {
    RepositoryItemWriter<H2Book> writer = new RepositoryItemWriter<>();
    writer.setMethodName("save");
    writer.setRepository(h2BookRepository);
    return writer;
  }

  @Bean
  public Step bookStep() {
    return stepBuilderFactory.get("genreStep")
            .<MongoBook, H2Book>chunk(CHUNK_SIZE)
            .reader(bookMongoItemReader())
            .processor(bookItemProcessor())
            .writer(bookItemWriter())
            .allowStartIfComplete(true)
            .build();
  }

  @Bean
  public Job transferJob() {
    return jobBuilderFactory.get("transferJob")
            .start(authorStep())
            .next(genreStep())
            .next(bookStep())
            .build();
  }
}
