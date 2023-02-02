package com.beeline.cc_question.services.interview.impl;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.dtos.interview.EssayDto;
import com.beeline.cc_question.entities.interview.Essay;
import com.beeline.cc_question.entities.interview.QuestionType;
import com.beeline.cc_question.repos.interview.EssayRepo;
import com.beeline.cc_question.services.candidate.impl.CandidateServiceImpl;
import com.beeline.cc_question.services.interview.EssayService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class EssayServiceImpl implements EssayService {
    private final EssayRepo essayRepo;
    private final CandidateServiceImpl candidateService;
    private final QuestionServiceImpl questionService;

    public EssayServiceImpl(EssayRepo essayRepo, CandidateServiceImpl candidateService, QuestionServiceImpl questionService) {
        this.essayRepo = essayRepo;
        this.candidateService = candidateService;
        this.questionService = questionService;
    }

    @Override
    public boolean save(EssayDto essayDto, Long candidate_id) {
        Candidate candidate = candidateService.candidateById(candidate_id);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        if (candidate != null){
            Essay essay = new Essay(essayDto.getTheme(), essayDto.getEssay(), now, candidate, essayDto.getPosition());
            add(essay);
            return true;
        }
        return false;
    }

    @Override
    public void add(Essay essay) {
        Long candidateType_id = essay.getCandidate().getCandidateType().getId();
        Long maxPosition = questionService.maxPosition(QuestionType.INTERVIEW.toString(), candidateType_id);
        if (essay.getPosition() >= maxPosition){
            candidateService.setStage(essay.getCandidate(), "completed");
        }
        essayRepo.save(essay);
    }

    @Override
    public Long position(Long candidate_id) {
        Long position = essayRepo.position(candidate_id);
        if (position != null){
            return position;
        }
        return 0L;
    }
}
