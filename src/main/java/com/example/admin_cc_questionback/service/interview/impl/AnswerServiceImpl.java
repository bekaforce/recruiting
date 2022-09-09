package com.example.admin_cc_questionback.service.interview.impl;

import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.interview.Answer;
import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.repository.interview.AnswerRepo;
import com.example.admin_cc_questionback.service.interview.AnswerService;
import com.example.admin_cc_questionback.service.loggers.impl.AnswerLoggerServiceImpl;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepo answerRepo;
    private final QuestionServiceImpl questionService;
    private final AnswerLoggerServiceImpl answerLoggerService;

    public AnswerServiceImpl(AnswerRepo answerRepo, QuestionServiceImpl questionService, AnswerLoggerServiceImpl answerLoggerService) {
        this.answerRepo = answerRepo;
        this.questionService = questionService;
        this.answerLoggerService = answerLoggerService;
    }


    @Override
    public Answer save(AnswerDto answerDto) {
        Question question = questionService.questionById(answerDto.getQuestion_id());
        if (question != null){
            Answer answer = answerRepo.save(new Answer(answerDto.getContent(), question,answerDto.isCorrect()));
            saveCreatedAnswerToLogs(answer);
            return answer;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Answer answer = answerRepo.getAnswerById(id);
        if (answer != null){
            saveDeletedAnswerToLogs(answer);
            answerRepo.deleteById(id);
            return true;
        }
            return false;
    }

    @Override
    public Answer update(ContentDto content, Long id) {
        Answer answer = answerById(id);
        if (answer != null){
            saveUpdatedAnswerToLogs(content, answer);
            answer.setContent(content.getContent());
            answer.setCorrect(content.isCorrect());
            answerRepo.save(answer);
            return answer;
        }
        return null;
    }

    @Override
    public Answer answerById(Long answer_id) {
        return answerRepo.getAnswerById(answer_id);
    }

    @Override
    public void saveCreatedAnswerToLogs(Answer answer) {
        answerLoggerService.save(answer, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedAnswerToLogs(Answer answer) {
        answerLoggerService.save(answer, LoggerStatus.DELETED);
    }

    @Override
    public void saveUpdatedAnswerToLogs(ContentDto content, Answer answer) {
        answerLoggerService.saveUpdate(content, answer);
    }
}
