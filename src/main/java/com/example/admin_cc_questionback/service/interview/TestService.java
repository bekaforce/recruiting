package com.example.admin_cc_questionback.service.interview;

import com.example.admin_cc_questionback.entities.dtos.ResultDto;

import java.util.List;

public interface TestService {
    Long percentage(Long candidate_id);
    List<ResultDto> result(Long candidate_id);
}
