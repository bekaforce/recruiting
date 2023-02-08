package com.example.admin_cc_questionback.entities.candidate;

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
