package ru.otus.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.h2.domain.H2Book;
import ru.otus.h2.repository.H2BookRepository;
import ru.otus.h2.service.H2BookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
@DisplayName("Spring Shell")
class ProcessEntitiesTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private H2BookService h2BookService;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @DisplayName("переносит данные из mongo в h2")
    @Test
    void shouldProcessEntities() throws Exception {
        Job transferJob = jobLauncherTestUtils.getJob();
        assertThat(transferJob).isNotNull();

        JobExecution execution = jobLauncherTestUtils.launchJob(new JobParameters());

        assertThat(execution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        List<H2Book> books = h2BookService.getAll();
        assertThat(books)
                .hasSize(2).extracting(H2Book::getExtId)
                .anyMatch(value -> value.matches("1L"))
                .anyMatch(value -> value.matches("2L"));
    }
}