package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.homework.config.QuizProperties;

@SpringBootApplication
@EnableConfigurationProperties({QuizProperties.class})
public class Hw04ShellApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw04ShellApplication.class, args);
	}

}
