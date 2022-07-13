package com.beeline.cc_question.services.candidate.impl.recaptcha;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponse {
    private boolean success;
    private String hostname;
    private String action;
    private float score;
    private String challenge_ts;
    @JsonProperty("error-codes")
    private List<String> errorCodes;
}
