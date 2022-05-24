package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Question;
import com.beeline.cc_question.entities.QuestionType;
import com.beeline.cc_question.repos.QuestionRepo;
import com.beeline.cc_question.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepo questionRepo;

    public QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }


    @Override
    public List<Question> getQuestionsForTest() {
        return questionRepo.findAllByQuestionType(QuestionType.TEST.toString());
    }

    @Override
    public List<Question> getQuestionsForVideo() {
        return questionRepo.findAllByQuestionType(QuestionType.VIDEO.toString());
    }

    @Override
    public Question questionById(Long id) {
        return questionRepo.getQuestionById(id);
    }
}
