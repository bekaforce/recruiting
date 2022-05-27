package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.Answer;
import com.example.admin_cc_questionback.entities.Question;
import com.example.admin_cc_questionback.repository.AnswerRepo;
import com.example.admin_cc_questionback.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepo answerRepo;
    private final QuestionServiceImpl questionService;

    public AnswerServiceImpl(AnswerRepo answerRepo, QuestionServiceImpl questionService) {
        this.answerRepo = answerRepo;
        this.questionService = questionService;
    }


    @Override
    public Answer addAnswer(AnswerDto answerDto) {
        Question question = questionService.getQuestionById(answerDto.getQuestion_id());
        if (question != null){
            return answerRepo.save(new Answer(answerDto.getContent(), question));
        }
        return null;
    }

    @Override
    public boolean deleteAnswer(Long id) {
        if (answerRepo.getAnswerById(id) != null){
            answerRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Answer updateQuestion(ContentDto content, Long id) {
        Answer answer = getAnswerById(id);
        if (answer != null){
            answer.setContent(content.getContent());
            answerRepo.save(answer);
            return answer;
        }
        else {
            return null;
        }
    }

    @Override
    public Answer getAnswerById(Long answer_id) {
        return answerRepo.getAnswerById(answer_id);
    }
}
