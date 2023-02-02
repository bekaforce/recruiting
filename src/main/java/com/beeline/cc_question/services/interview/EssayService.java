package com.beeline.cc_question.services.interview;

import com.beeline.cc_question.entities.dtos.interview.EssayDto;
import com.beeline.cc_question.entities.interview.Essay;

public interface EssayService {
    boolean save(EssayDto essayDto, Long participant_id);
    void add(Essay essay);
    Long position(Long candidate_id);
}
