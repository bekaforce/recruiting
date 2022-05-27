package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.Answer;

public interface AnswerService {
    Answer addAnswer(AnswerDto answerDto);
    boolean deleteAnswer(Long id);
    Answer updateQuestion(ContentDto content, Long id);
    Answer getAnswerById(Long answer_id);
}
