package com.example.admin_cc_questionback.service.interview;

import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.entities.interview.Answer;
import com.example.admin_cc_questionback.entities.interview.Question;

public interface AnswerService {
    Answer save(AnswerDto answerDto);
    boolean delete(Long id);
    Answer update(ContentDto content, Long id);
    Answer answerById(Long answer_id);
    void saveCreatedAnswerToLogs(Answer answer);
    void saveDeletedAnswerToLogs(Answer answer);
    void saveUpdatedAnswerToLogs(ContentDto content, Answer answer);
}