package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.entities.loggers.QuestionLogger;

public interface QuestionLoggerService {
    QuestionLogger logger(String questionText, String candidateType, String questionType, String status, String position);
    void saveDelete(String questionText, String candidateType, String milliseconds, String key, String questionType, String status, String position);
    void save(String questionText, String candidateType, String questionType, String status, String position);
    void saveUpdate(QuestionUpdateDto questionUpdateDto, Question question);
}
