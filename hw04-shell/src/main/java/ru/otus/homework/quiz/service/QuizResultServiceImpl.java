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
    private final int passPercent;
    private final Locale locale;
    private final MessageSource messageSource;
    private final QuizResult quizResult;

    public QuizResultServiceImpl(IOService ioService, QuizProperties quizProperties, MessageSource messageSource) {
        this.ioService = ioService;
        this.passPercent = quizProperties.getPassPercent();
        this.locale = quizProperties.getLocale();
        this.messageSource = messageSource;
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
                                new String[]{String.valueOf(quizResult.getOverallCount()), String.valueOf(quizResult.getCorrectCount())}, locale));
        if (quizResult.getCorrectCount() * 100 / quizResult.getOverallCount() >= passPercent) {
            ioService.println(messageSource.getMessage("message.congrats", null, locale));
        } else {
            ioService.println(messageSource.getMessage("message.sorry", null, locale));
        }
    }
}
