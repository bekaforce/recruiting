package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.interview.Answer;

public interface AnswerLoggerService {
    void save(String content, String question, String correct, String status);
    void saveUpdate(String content, String question, String correct, Answer answer);
}
