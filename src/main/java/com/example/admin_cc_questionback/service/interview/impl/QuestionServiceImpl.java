package com.example.admin_cc_questionback.service.interview.impl;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.candidate.QuestionType;
import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.repository.interview.QuestionRepo;
import com.example.admin_cc_questionback.service.candidate.impl.CandidateTypeServiceImpl;
import com.example.admin_cc_questionback.service.interview.QuestionService;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerStatus;
import com.example.admin_cc_questionback.service.impl.loggers.QuestionLoggerServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepo questionRepo;
    private final CandidateTypeServiceImpl candidateTypeService;
    private final QuestionLoggerServiceImpl questionLoggerService;

    public QuestionServiceImpl(QuestionRepo questionRepo, CandidateTypeServiceImpl candidateTypeService, QuestionLoggerServiceImpl questionLoggerService) {
        this.questionRepo = questionRepo;
        this.candidateTypeService = candidateTypeService;
        this.questionLoggerService = questionLoggerService;
    }

    @Override
    public Question saveQuestionForTest(QuestionDto questionDto) {
        return save(QuestionType.TEST.toString(), questionDto);
    }

    @Override
    public Question saveQuestionForVideo(QuestionDto questionDto) {
        return save(QuestionType.VIDEO.toString(), questionDto);
    }

    @Override
    public Question save(String questionType, QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionText(questionDto.getQuestionText());
        question.setQuestionType(QuestionType.valueOf(questionType));
        question.setMilliseconds(null);
        CandidateType candidateType = candidateTypeService.candidateTypeById(questionDto.getCandidateType_id());
        if (candidateType == null){
            return null;
        }
        question.setCandidateType(candidateType);
        long position = 0;
        if (!questionRepo.findAllByQuestionType(questionType, candidateType.getId()).isEmpty()){
            position = questionRepo.maxPosition(questionType, candidateType.getId());
        }
        question.setPosition(position + 1);
        question.setKey(false);
        questionRepo.save(question);
        questionLoggerService.save(question.getQuestionText(), question.getCandidateType().getCandidateType(), question.getQuestionType().toString(), LoggerStatus.CREATED, question.getPosition().toString());
        return question;
    }

    @Override
    public List<Question> getQuestions(String questionType, Long candidateType_id) {
        return questionRepo.findAllByQuestionType(questionType, candidateType_id);
    }

    @Override
    public List<Question> getQuestionsForTest(Long candidateType_id) {
        return getQuestions(QuestionType.TEST.toString(), candidateType_id);
    }

    @Override
    public List<Question> getQuestionsForVideo(Long candidateType_id) {
        return getQuestions(QuestionType.VIDEO.toString(), candidateType_id);
    }

    @Override
    public Question questionById(Long question_id) {
        return questionRepo.getQuestionById(question_id);
    }

    @Override
    public boolean delete(Long question_id, String questionType) {
        Question question = questionRepo.getQuestionById(question_id);
        if (question != null){
            long positionId = question.getPosition();
            questionRepo.deleteById(question_id);
            questionLoggerService.saveDelete(question.getQuestionText(), question.getCandidateType().getCandidateType(), String.valueOf(question.getMilliseconds()), String.valueOf(question.isKey()), question.getQuestionType().toString(), LoggerStatus.DELETED, String.valueOf(question.getPosition()));
            CandidateType candidateType = question.getCandidateType();
            List<Question> questions = findAllByQuestionAndCandidateTypes(questionType, candidateType.getId());
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
    public List<Question> findAllByQuestionAndCandidateTypes(String questionType, Long candidateType_id) {
        return questionRepo.findAllByQuestionType(questionType, candidateType_id);
    }

    @Override
    public boolean deleteQuestionForTest(Long question_id) {
        return delete(question_id, QuestionType.TEST.toString());
    }

    @Override
    public boolean deleteQuestionForVideo(Long question_id) {
        return delete(question_id, QuestionType.VIDEO.toString());
    }

    @Override
    public Question update(QuestionUpdateDto questionUpdateDto, Long question_id) {
        Question question = questionById(question_id);
        if (question != null){
            questionLoggerService.saveUpdate(questionUpdateDto, question);
            question.setQuestionText(questionUpdateDto.getQuestionText());
            question.setKey(questionUpdateDto.isKey());
            question.setMilliseconds(questionUpdateDto.getMilliseconds());
            questionRepo.save(question);
            return question;
        }
        return null;
    }
}

