package com.beeline.cc_question.services.interview;

import com.beeline.cc_question.entities.interview.Question;

import java.util.List;

public interface QuestionService {
    List<Question> questionsForTest(Long candidateType_id);
    List<Question> questionsForVideo(Long candidateType_id);
    List<Question> questions(String questionType, Long candidateType_id);
    Long maxPosition(String questionType, Long candidateType_id);
}
