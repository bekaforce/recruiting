package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.dtos.TestDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.Test;
import com.beeline.cc_question.repos.TestRepo;
import com.beeline.cc_question.services.TestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Test> saveTest(List<TestDto> testDtoList, Long candidateId) {
        List<Test> result = new ArrayList<>();
        Candidate candidate = candidateService.getCandidateById(candidateId);
        testDtoList.forEach(testDto -> {
            LocalDateTime now = LocalDateTime.now();
            Test test = new Test(testDto.getQuestion(), testDto.getAnswer(), candidate, now);
            testRepo.save(test);
            result.add(test);
        });
        return result;
    }


}
