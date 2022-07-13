package com.beeline.cc_question.services.interview.impl;

import com.beeline.cc_question.entities.dtos.interview.ResultDto;
import com.beeline.cc_question.entities.dtos.interview.TestDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.interview.Test;
import com.beeline.cc_question.repos.interview.TestRepo;
import com.beeline.cc_question.services.interview.TestService;
import com.beeline.cc_question.services.candidate.impl.CandidateServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepo testRepo;
    private final CandidateServiceImpl candidateService;

    public TestServiceImpl(TestRepo testRepo, CandidateServiceImpl candidateService) {
        this.testRepo = testRepo;
        this.candidateService = candidateService;
    }

    @Override
    public String save(List<TestDto> testDtoList, Long candidateId) {
        Candidate candidate = candidateService.candidateById(candidateId);
        if (candidate != null){
            testDtoList.forEach(testDto -> {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
                Test test = new Test(testDto.getQuestion(), testDto.getAnswer(), candidate, now, testDto.isCorrect(), testDto.isKey());
                testRepo.save(test);
            });
            candidateService.setStage(candidate, "video");
            return stage(candidateId);
        }
        return null;
    }

    @Override
    public Long percentage(Long candidate_id) {
        List<ResultDto> result = result(candidate_id);
        Long correct = result.get(0).getAmount();;
        Long all = result.get(1).getAmount();
        double number = (double) correct / all * 100;
        return Math.round(number);
    }

    @Override
    public List<ResultDto> result(Long candidate_id) {
        return testRepo.result(candidate_id);
    }

    @Override
    public String stage(Long candidate_id) {
        Candidate candidate = candidateService.candidateById(candidate_id);
        if (percentage(candidate_id) < 70){
            candidateService.setStage(candidateService.candidateById(candidate_id), "failed");
            return candidate.getStage();
        }
        return candidate.getStage();
    }
}
