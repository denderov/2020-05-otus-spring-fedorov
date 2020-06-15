package ru.otus.homework.config;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(CsvProperties.class)
@TestPropertySource(locations = "classpath:/application.yml")
public class CsvPropertiesTest {

  @Autowired
  private CsvProperties csvProperties;

  @Test
  void readingPropertyTest() {
    assertThat(csvProperties.getFileName()).isEqualTo("correct_test.csv");
  }

}
