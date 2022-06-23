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
    public List<Question> questionsForTest(Long candidateType_id) {
        return questions(QuestionType.TEST.toString(), candidateType_id);
    }

    @Override
    public List<Question> questionsForVideo(Long candidateType_id) {
        return questions(QuestionType.VIDEO.toString(), candidateType_id);
    }

    @Override
    public List<Question> questions(String questionType, Long candidateType_id) {
        return questionRepo.allByQuestionType(questionType, candidateType_id);
    }

    @Override
    public Long maxPosition(String questionType, Long candidateType_id) {
        return questionRepo.maxPosition(questionType, candidateType_id);
    }
}
