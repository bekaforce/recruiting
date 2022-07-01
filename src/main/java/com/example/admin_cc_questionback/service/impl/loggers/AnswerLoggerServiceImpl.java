package com.example.admin_cc_questionback.service.impl.loggers;

import com.example.admin_cc_questionback.entities.interview.Answer;
import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.repository.loggers.AnswerLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.AnswerLoggerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerLoggerServiceImpl implements AnswerLoggerService {

    private final AnswerLoggerRepo answerLoggerRepo;
    private final LoggerService loggerService;

    public AnswerLoggerServiceImpl(AnswerLoggerRepo answerLoggerRepo, LoggerService loggerService) {
        this.answerLoggerRepo = answerLoggerRepo;
        this.loggerService = loggerService;
    }


    @Override
    public void save(String content, String question, String correct, String status) {
        AnswerLogger answerLogger = new AnswerLogger();
        answerLogger.setContent(content);
        answerLogger.setQuestion(question);
        answerLogger.setCorrect(correct);
        answerLogger.setStatus(status);
        answerLogger.setLogin(loggerService.login());
        answerLogger.setDateTime(loggerService.bishkekNow());
        answerLoggerRepo.save(answerLogger);
    }

    @Override
    public void saveUpdate(String content, String question, String correct, Answer answer) {
        AnswerLogger answerLogger = new AnswerLogger();
        answerLogger.setContent(loggerService.setParam(answer.getContent(), content));
        answerLogger.setCorrect(loggerService.setParam(String.valueOf(answer.isCorrect()), correct));
        answerLogger.setQuestion(question);
        answerLogger.setStatus(LoggerStatus.UPDATED);
        answerLogger.setDateTime(loggerService.bishkekNow());
        answerLogger.setLogin(loggerService.login());
        answerLoggerRepo.save(answerLogger);
    }
}
