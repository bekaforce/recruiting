package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Question;

import java.util.List;

public interface QuestionService {
    Question saveQuestionForTest(String questionText);
    Question saveQuestionForVideo(String questionText);
    Question saveQuestion(String questionType, String questionText);
    List<Question> getQuestions(String questionType);
    List<Question> getQuestionsForTest();
    List<Question> getQuestionsForVideo();
    Question getQuestionById(Long question_id);
    boolean deleteQuestion(Long question_id, String questionType);
    List<Question> findAllByQuestionType(String questionType);
    boolean deleteQuestionForTest(Long question_id);
    boolean deleteQuestionForVideo(Long question_id);
    Question updateQuestion(String questionText, Long question_id);
}
