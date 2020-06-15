package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.homework.config.CsvProperties;
import ru.otus.homework.config.QuizProperties;
import ru.otus.homework.quiz.service.QuizService;

@SpringBootApplication
@EnableConfigurationProperties({CsvProperties.class, QuizProperties.class})
public class Hw03SpringBootApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(Hw03SpringBootApplication.class, args);

		QuizService quizService = context.getBean(QuizService.class);

		quizService.readQuiz();
		quizService.runTest();
	}

}
