package com.example.admin_cc_questionback.service.interview.impl;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.dtos.QuestionInterviewDto;
import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.candidate.QuestionType;
import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.repository.interview.QuestionRepo;
import com.example.admin_cc_questionback.service.candidate.impl.CandidateTypeServiceImpl;
import com.example.admin_cc_questionback.service.interview.QuestionService;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import com.example.admin_cc_questionback.service.loggers.impl.QuestionLoggerServiceImpl;
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
        return save(QuestionType.TEST.toString(), questionDto, 0L, questionDto.isKey());
    }

    @Override
    public Question saveQuestionForInterview(QuestionInterviewDto questionInterviewDto) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionText(questionInterviewDto.getQuestionText());
        if (questionInterviewDto.getSeconds() == null){
            questionInterviewDto.setSeconds(0L);
        }
        questionDto.setCandidateType_id(questionInterviewDto.getCandidateType_id());
        Question question = save(QuestionType.INTERVIEW.toString(), questionDto, questionInterviewDto.getSeconds() * 1000, questionInterviewDto.isKey());
        if (question != null){
            question.setMilliseconds(questionInterviewDto.getSeconds());
            return question;
        }
        return null;
    }

    @Override
    public Question save(String questionType, QuestionDto questionDto, Long milliseconds, boolean key) {
        Question question = new Question();
        question.setQuestionText(questionDto.getQuestionText());
        question.setQuestionType(QuestionType.valueOf(questionType));
        question.setMilliseconds(milliseconds);
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
        question.setKey(key);
        questionRepo.save(question);
        saveCreatedQuestionToLogs(question);
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
    public List<Question> getQuestionsForInterview(Long candidateType_id) {
        return getQuestions(QuestionType.INTERVIEW.toString(), candidateType_id);
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
            saveDeletedQuestionToLogs(question);
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
    public boolean deleteQuestionForInterview(Long question_id) {
        return delete(question_id, QuestionType.INTERVIEW.toString());
    }

    @Override
    public Question update(QuestionUpdateDto questionUpdateDto, Long question_id) {
        Question question = questionById(question_id);
        if (question != null){
            saveUpdatedQuestionToLogs(questionUpdateDto, question);
            question.setQuestionText(questionUpdateDto.getQuestionText());
            question.setKey(questionUpdateDto.isKey());
            question.setMilliseconds(questionUpdateDto.getSeconds() * 1000);
            questionRepo.save(question);
            return question;
        }
        return null;
    }

    @Override
    public void saveCreatedQuestionToLogs(Question question) {
        questionLoggerService.save(question, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedQuestionToLogs(Question question) {
        questionLoggerService.saveDelete(question, LoggerStatus.DELETED);
    }

    @Override
    public void saveUpdatedQuestionToLogs(QuestionUpdateDto questionUpdateDto, Question question) {
        questionLoggerService.saveUpdate(questionUpdateDto, question);
    }
}
