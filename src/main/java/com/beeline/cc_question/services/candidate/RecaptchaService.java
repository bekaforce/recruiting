package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.recaptcha.Recaptcha;

public interface RecaptchaService {
    Recaptcha recaptchaByName(String name);
}
