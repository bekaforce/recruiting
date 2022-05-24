package com.beeline.cc_question.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Setter
@Getter
public class MailMessage {
    private String templateId;
    private Map<String, Object> paramMap;
    private String subject;
    private String from;
    private String to;
}
