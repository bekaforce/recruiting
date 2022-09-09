package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.interview.Answer;
import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;

import java.util.List;

public interface AnswerLoggerService {
    void save(Answer answer, String status);
    void saveUpdate(ContentDto content, Answer answer);
    List<AnswerLogger> all();
}
