package com.example.admin_cc_questionback.service.interview;

import com.example.admin_cc_questionback.entities.dtos.QuestionVideoDto;
import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;

import java.util.List;

public interface QuestionService {
    Question saveQuestionForTest(QuestionDto questionDto);
    Question saveQuestionForVideo(QuestionVideoDto questionVideoDto);
    Question save(String questionType, QuestionDto questionDto, Long milliseconds);
    List<Question> getQuestions(String questionType, Long candidateType_id);
    List<Question> getQuestionsForTest(Long candidateType_id);
    List<Question> getQuestionsForVideo(Long candidateType_id);
    Question questionById(Long question_id);
    boolean delete(Long question_id, String questionType);
    List<Question> findAllByQuestionAndCandidateTypes(String questionType, Long candidateType_id);
    boolean deleteQuestionForTest(Long question_id);
    boolean deleteQuestionForVideo(Long question_id);
    Question update(QuestionUpdateDto questionUpdateDto, Long question_id);
    void saveCreatedQuestionToLogs(Question question);
    void saveDeletedQuestionToLogs(Question question);
    void saveUpdatedQuestionToLogs(QuestionUpdateDto questionUpdateDto, Question question);
}
