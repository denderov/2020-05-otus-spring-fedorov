package ru.otus.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.homework.quiz.service.QuizService;

@EnableAspectJAutoProxy
@ComponentScan
public class Main {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(Main.class);

    QuizService quizService = context.getBean(QuizService.class);

    quizService.readQuiz();
    quizService.runTest();

  }

}
