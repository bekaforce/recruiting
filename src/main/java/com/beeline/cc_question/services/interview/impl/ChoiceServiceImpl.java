package com.beeline.cc_question.services.interview.impl;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.interview.QuestionType;
import com.beeline.cc_question.services.candidate.impl.CandidateServiceImpl;
import com.beeline.cc_question.services.interview.ChoiceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoiceServiceImpl implements ChoiceService {
    private final CandidateServiceImpl candidateService;

    public ChoiceServiceImpl(CandidateServiceImpl candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String chooseEssayOrVideo(Long candidateId, String interview) {
        Candidate candidate = candidateService.candidateById(candidateId);
        if (candidate != null){
            candidateService.setStage(candidate, interview);
            return interview;
        }
        return null;
    }

    @Override
    public List<String> getEssayAndVideo() {
        List<String> result = new ArrayList<>();
        result.add("essay");
        result.add("video");
        return result;
    }
}
