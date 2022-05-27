package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Question;
import com.example.admin_cc_questionback.entities.QuestionType;
import com.example.admin_cc_questionback.repository.QuestionRepo;
import com.example.admin_cc_questionback.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepo questionRepo;

    public QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Override
    public Question saveQuestionForTest(String questionText) {
        return saveQuestion(QuestionType.TEST.toString(), questionText);
    }

    @Override
    public Question saveQuestionForVideo(String questionText) {
        return saveQuestion(QuestionType.VIDEO.toString(), questionText);
    }

    @Override
    public Question saveQuestion(String questionType, String questionText) {
        Question question = new Question();
        question.setQuestionText(questionText);
        question.setQuestionType(QuestionType.valueOf(questionType));
        long position = 0;
        if (!questionRepo.findAllByQuestionType(questionType).isEmpty()){
            position = questionRepo.maxPosition(questionType);
        }
        question.setPosition(position + 1);
        return questionRepo.save(question);
    }

    @Override
    public List<Question> getQuestions(String questionType) {
        return questionRepo.findAllByQuestionType(questionType);
    }

    @Override
    public List<Question> getQuestionsForTest() {
        return getQuestions(QuestionType.TEST.toString());
    }

    @Override
    public List<Question> getQuestionsForVideo() {
        return getQuestions(QuestionType.VIDEO.toString());
    }

    @Override
    public Question getQuestionById(Long question_id) {
        return questionRepo.getQuestionById(question_id);
    }

    @Override
    public boolean deleteQuestion(Long question_id, String questionType) {
        Question question = questionRepo.getQuestionById(question_id);
        if (question != null){
            long positionId = question.getPosition();
            questionRepo.deleteById(question_id);
            List<Question> questions = findAllByQuestionType(questionType);
            for (Question positionQ : questions){
                if (positionQ.getPosition() > positionId){
                    positionQ.setPosition(positionQ.getPosition() - 1);
                    questionRepo.save(positionQ);
                }
            }
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public List<Question> findAllByQuestionType(String questionType) {
        return questionRepo.findAllByQuestionType(questionType);
    }

    @Override
    public boolean deleteQuestionForTest(Long question_id) {
        return deleteQuestion(question_id, QuestionType.TEST.toString());
    }

    @Override
    public boolean deleteQuestionForVideo(Long question_id) {
        return deleteQuestion(question_id, QuestionType.VIDEO.toString());
    }

    @Override
    public Question updateQuestion(String questionText, Long question_id) {
        Question question = getQuestionById(question_id);
        if (question != null){
            question.setQuestionText(questionText);
            questionRepo.save(question);
            return question;
        }
        else {
            return null;
        }
    }
}

