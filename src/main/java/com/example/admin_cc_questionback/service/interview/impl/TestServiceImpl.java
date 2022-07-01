package com.example.admin_cc_questionback.service.interview.impl;

import com.example.admin_cc_questionback.repository.interview.TestRepo;
import com.example.admin_cc_questionback.entities.dtos.ResultDto;
import com.example.admin_cc_questionback.service.interview.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepo testRepo;

    public TestServiceImpl(TestRepo testRepo) {
        this.testRepo = testRepo;
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
}
