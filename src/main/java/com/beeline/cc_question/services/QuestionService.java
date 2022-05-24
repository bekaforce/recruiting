package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsForTest();
    List<Question> getQuestionsForVideo();
    Question questionById(Long id);
}
