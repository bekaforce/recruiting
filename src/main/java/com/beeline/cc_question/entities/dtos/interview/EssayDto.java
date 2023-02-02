package com.beeline.cc_question.entities.dtos.interview;

import lombok.Data;

@Data
public class EssayDto {
    private String theme;
    private String essay;
    private Long position;
    private Long candidate_id;
}
