package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.dtos.ResultDto;
import com.beeline.cc_question.dtos.TestDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.Test;
import com.beeline.cc_question.repos.TestRepo;
import com.beeline.cc_question.services.TestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public List<Test> save(List<TestDto> testDtoList, Long candidateId) {
        List<Test> result = new ArrayList<>();
        Candidate candidate = candidateService.candidateById(candidateId);
        if (candidate != null){
            testDtoList.forEach(testDto -> {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
                Test test = new Test(testDto.getQuestion(), testDto.getAnswer(), candidate, now, testDto.isCorrect(), testDto.isKey());
                testRepo.save(test);
                result.add(test);
            });
            candidateService.setStage(candidate, "video");
            return result;
        }
        return null;
    }

    @Override
    public Long percentage(Long candidate_id) {
        List<ResultDto> result = result(candidate_id);
        Long correct = result.get(0).getAmount();;
        Long all = result.get(1).getAmount();
        double number = (double) correct / all * 100;
        if (Math.round(number) < 70){
            candidateService.setStage(candidateService.candidateById(candidate_id), "failed");
        }
        return Math.round(number);
    }

    @Override
    public List<ResultDto> result(Long candidate_id) {
        return testRepo.result(candidate_id);
    }
}
