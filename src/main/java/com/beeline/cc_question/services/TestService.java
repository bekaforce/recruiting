package com.beeline.cc_question.services;

import com.beeline.cc_question.dtos.TestDto;
import com.beeline.cc_question.entities.Test;

import java.util.List;

public interface TestService {
    List<Test> saveTest(List<TestDto> testDtos, Long candidateId);
}
