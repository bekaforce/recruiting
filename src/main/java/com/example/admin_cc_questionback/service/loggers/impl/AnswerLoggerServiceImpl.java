package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.interview.Answer;
import com.example.admin_cc_questionback.entities.interview.VideoResult;
import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.repository.loggers.AnswerLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.AnswerLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerLoggerServiceImpl implements AnswerLoggerService {

    private final AnswerLoggerRepo answerLoggerRepo;
    private final LoggerService loggerService;

    public AnswerLoggerServiceImpl(AnswerLoggerRepo answerLoggerRepo, LoggerService loggerService) {
        this.answerLoggerRepo = answerLoggerRepo;
        this.loggerService = loggerService;
    }


    @Override
    public void save(Answer answer, String status) {
        AnswerLogger answerLogger = new AnswerLogger();
        answerLogger.setContent(answer.getContent());
        answerLogger.setQuestion(answer.getQuestion().getQuestionText());
        answerLogger.setCorrect(String.valueOf(answer.isCorrect()));
        answerLogger.setStatus(status);
        answerLogger.setLogin(loggerService.login());
        answerLogger.setDateTime(loggerService.bishkekNow());
        answerLoggerRepo.save(answerLogger);
    }

    @Override
    public void saveUpdate(ContentDto content, Answer answer) {
        AnswerLogger answerLogger = new AnswerLogger();
        answerLogger.setContent(loggerService.setParam(answer.getContent(), content.getContent()));
        answerLogger.setCorrect(loggerService.setParam(String.valueOf(answer.isCorrect()), String.valueOf(content.isCorrect())));
        answerLogger.setQuestion(answer.getQuestion().getQuestionText());
        answerLogger.setStatus(LoggerStatus.UPDATED);
        answerLogger.setDateTime(loggerService.bishkekNow());
        answerLogger.setLogin(loggerService.login());
        answerLoggerRepo.save(answerLogger);
    }

    @Override
    public List<AnswerLogger> all() {
        return answerLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(AnswerLogger::getId).reversed()).collect(Collectors.toList());
    }
}
