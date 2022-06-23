package com.beeline.cc_question.services;

import com.beeline.cc_question.dtos.ResultDto;
import com.beeline.cc_question.dtos.TestDto;
import com.beeline.cc_question.entities.Test;

import java.util.List;

public interface TestService {
    List<Test> save(List<TestDto> testDtoList, Long candidateId);
    Long percentage(Long candidate_id);
    List<ResultDto> result(Long candidate_id);
}
