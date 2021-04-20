package ru.otus.homework.quiz.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.common.IOService;
import ru.otus.homework.config.QuizProperties;
import ru.otus.homework.quiz.domain.QuizAnswer;
import ru.otus.homework.quiz.domain.QuizResult;
import ru.otus.homework.quiz.domain.TestQuestion;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    private final IOService ioService;
    private final MessageSource messageSource;
    private final QuizProperties quizProperties;
    private final QuizResult quizResult;

    public QuizResultServiceImpl(IOService ioService, QuizProperties quizProperties, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        this.quizProperties = quizProperties;
        this.quizResult = new QuizResult();
    }

    @Override
    public void evaluateResult(List<TestQuestion> testQuestions) {
        quizResult.setOverallCount(testQuestions.size());
        int correctCount = 0;
        for (TestQuestion testQuestion :
                testQuestions
        ) {
            List<QuizAnswer> quizAnswers = testQuestion.getQuizQuestion().getAnswers();
            long incorrectCount = IntStream.range(0, quizAnswers.size())
                    .filter(
                            (i) -> quizAnswers.get(i).isCorrect()
                                    ^ testQuestion.getReceivedAnswers().contains(i + 1))
                    .count();
            if (incorrectCount == 0) {
                correctCount++;
            }
        }
        quizResult.setCorrectCount(correctCount);
        ioService.println(
                messageSource
                        .getMessage("message.count",
                                new String[]{String.valueOf(quizResult.getOverallCount()), String.valueOf(quizResult.getCorrectCount())}, quizProperties.getLocale()));
        if (quizResult.getCorrectCount() * 100 / quizResult.getOverallCount() >= quizProperties.getPassPercent()) {
            ioService.println(messageSource.getMessage("message.congrats", null, quizProperties.getLocale()));
        } else {
            ioService.println(messageSource.getMessage("message.sorry", null, quizProperties.getLocale()));
        }
    }
}
