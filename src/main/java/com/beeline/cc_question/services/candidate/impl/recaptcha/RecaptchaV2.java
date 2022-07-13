package com.beeline.cc_question.services.candidate.impl.recaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaV2 {
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    public boolean isValidCaptcha(String captcha) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String completeUrl = url + "?secret=" + recaptchaSecret + "&response=" + captcha;
        CaptchaResponse resp = restTemplate.postForObject(completeUrl, null, CaptchaResponse.class);
        return resp.isSuccess();
    }
}
