package ru.otus.homework.quiz.service;

import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.TestQuestion;

import java.util.List;

public interface QuizResultService {
    void evaluateResult(List<TestQuestion> testQuestions);
}
