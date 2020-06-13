package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.homework.quiz.service.QuizService;

@SpringBootApplication
public class Hw03SpringBootApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(Hw03SpringBootApplication.class, args);

		QuizService quizService = context.getBean(QuizService.class);

		quizService.readQuiz();
		quizService.runTest();
	}

}
