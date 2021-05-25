package ru.otus;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class Hw18HystrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(Hw18HystrixApplication.class, args);
  }

}
