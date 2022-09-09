package com.beeline.cc_question.entities.dtos.candidate;

import lombok.Data;

@Data
public class FeedbackDto {
    private String name;
    private String email;
    private String comment;
    private String captcha;
}
