package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.entities.loggers.QuestionLogger;

import java.util.List;

public interface QuestionLoggerService {
    QuestionLogger logger(String questionText, String candidateType, String questionType, String status, String position);
    void saveDelete(Question question, String status);
    void save(Question question, String status);
    void saveUpdate(QuestionUpdateDto questionUpdateDto, Question question);
    List<QuestionLogger> all();
}
