package com.beeline.cc_question.services.interview;

import com.beeline.cc_question.entities.dtos.interview.ResultDto;
import com.beeline.cc_question.entities.dtos.interview.TestDto;

import java.util.List;

public interface TestService {
    String save(List<TestDto> testDtoList, Long candidateId);
    Long percentage(Long candidate_id);
    List<ResultDto> result(Long candidate_id);
    String stage(Long candidate_id);
}
