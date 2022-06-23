package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;

import java.util.List;

public interface QuestionService {
    Question saveQuestionForTest(QuestionDto questionDto);
    Question saveQuestionForVideo(QuestionDto questionDto);
    Question save(String questionType, QuestionDto questionDto);
    List<Question> getQuestions(String questionType, Long candidateType_id);
    List<Question> getQuestionsForTest(Long candidateType_id);
    List<Question> getQuestionsForVideo(Long candidateType_id);
    Question questionById(Long question_id);
    boolean delete(Long question_id, String questionType);
    List<Question> findAllByQuestionAndCandidateTypes(String questionType, Long candidateType_id);
    boolean deleteQuestionForTest(Long question_id);
    boolean deleteQuestionForVideo(Long question_id);
    Question update(QuestionUpdateDto questionUpdateDto, Long question_id);
}
