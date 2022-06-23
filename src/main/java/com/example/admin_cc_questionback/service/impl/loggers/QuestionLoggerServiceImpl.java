package com.example.admin_cc_questionback.service.impl.loggers;


import com.example.admin_cc_questionback.entities.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.entities.loggers.QuestionLogger;
import com.example.admin_cc_questionback.repository.loggers.QuestionLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.QuestionLoggerService;
import org.springframework.stereotype.Service;

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
    public void saveDelete(String questionText, String candidateType, String milliseconds, String key, String questionType, String status, String position) {
        QuestionLogger questionLogger = logger(questionText, candidateType, questionType, status, position);
        questionLogger.setMilliseconds(milliseconds);
        questionLogger.setKey(key);
        questionLoggerRepo.save(questionLogger);
    }

    @Override
    public void save(String questionText, String candidateType, String questionType, String status, String position) {
        QuestionLogger questionLogger = logger(questionText, candidateType, questionType, status, position);
        questionLoggerRepo.save(questionLogger);
    }

    @Override
    public void saveUpdate(QuestionUpdateDto questionUpdateDto, Question question) {
        QuestionLogger questionLogger = new QuestionLogger();
        questionLogger.setQuestionText(loggerService.setParam(question.getQuestionText(), questionUpdateDto.getQuestionText()));
        questionLogger.setMilliseconds(loggerService.setParam(String.valueOf(question.getMilliseconds()), String.valueOf(questionUpdateDto.getMilliseconds())));
        questionLogger.setKey(loggerService.setParam(String.valueOf(question.isKey()), String.valueOf(questionUpdateDto.isKey())));
        questionLogger.setCandidateType(question.getCandidateType().getCandidateType());
        questionLogger.setPosition(String.valueOf(question.getPosition()));
        questionLogger.setQuestionType(question.getQuestionType().toString());
        questionLogger.setLogin(loggerService.login());
        questionLogger.setDateTime(loggerService.bishkekNow());
        questionLogger.setStatus(LoggerStatus.UPDATED);
        questionLoggerRepo.save(questionLogger);
    }
}
