package com.example.admin_cc_questionback.service.loggers.impl;


import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.entities.loggers.QuestionLogger;
import com.example.admin_cc_questionback.repository.loggers.QuestionLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.QuestionLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionLoggerServiceImpl implements QuestionLoggerService {
    private final QuestionLoggerRepo questionLoggerRepo;
    private final LoggerService loggerService;


    public QuestionLoggerServiceImpl(QuestionLoggerRepo questionLoggerRepo, LoggerService loggerService) {
        this.questionLoggerRepo = questionLoggerRepo;
        this.loggerService = loggerService;
    }



    @Override
    public QuestionLogger logger(String questionText, String candidateType, String questionType, String status, String position) {
        QuestionLogger questionLogger = new QuestionLogger();
        questionLogger.setQuestionText(questionText);
        questionLogger.setCandidateType(candidateType);
        questionLogger.setLogin(loggerService.login());
        questionLogger.setDateTime(loggerService.bishkekNow());
        questionLogger.setQuestionType(questionType);
        questionLogger.setStatus(status);
        questionLogger.setPosition(position);
        return questionLogger;
    }

    @Override
    public void saveDelete(Question question, String status) {
        QuestionLogger questionLogger = logger(question.getQuestionText(), question.getCandidateType().getCandidateType(), question.getQuestionType().toString(), status, String.valueOf(question.getPosition()));
        questionLogger.setMilliseconds(String.valueOf(question.getMilliseconds()));
        questionLogger.setKey(String.valueOf(question.isKey()));
        questionLoggerRepo.save(questionLogger);
    }

    @Override
    public void save(Question question, String status) {
        QuestionLogger questionLogger = logger(question.getQuestionText(), question.getCandidateType().getCandidateType(), question.getQuestionType().toString(), status, question.getPosition().toString());
        questionLoggerRepo.save(questionLogger);
    }

    @Override
    public void saveUpdate(QuestionUpdateDto questionUpdateDto, Question question) {
        QuestionLogger questionLogger = new QuestionLogger();
        questionLogger.setQuestionText(loggerService.setParam(question.getQuestionText(), questionUpdateDto.getQuestionText()));
        questionLogger.setMilliseconds(loggerService.setParam(String.valueOf(question.getMilliseconds()), String.valueOf(questionUpdateDto.getSeconds())));
        questionLogger.setKey(loggerService.setParam(String.valueOf(question.isKey()), String.valueOf(questionUpdateDto.isKey())));
        questionLogger.setCandidateType(question.getCandidateType().getCandidateType());
        questionLogger.setPosition(String.valueOf(question.getPosition()));
        questionLogger.setQuestionType(question.getQuestionType().toString());
        questionLogger.setLogin(loggerService.login());
        questionLogger.setDateTime(loggerService.bishkekNow());
        questionLogger.setStatus(LoggerStatus.UPDATED);
        questionLoggerRepo.save(questionLogger);
    }

    @Override
    public List<QuestionLogger> all() {
        return questionLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(QuestionLogger::getId).reversed()).collect(Collectors.toList());
    }
}
