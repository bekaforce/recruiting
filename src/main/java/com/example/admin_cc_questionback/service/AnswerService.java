package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.Answer;

public interface AnswerService {
    Answer save(AnswerDto answerDto);
    boolean delete(Long id);
    Answer update(ContentDto content, Long id);
    Answer answerById(Long answer_id);
}
