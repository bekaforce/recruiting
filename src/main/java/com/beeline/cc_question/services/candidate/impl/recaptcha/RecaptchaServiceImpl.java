package com.beeline.cc_question.services.candidate.impl.recaptcha;

import com.beeline.cc_question.entities.candidate.recaptcha.Recaptcha;
import com.beeline.cc_question.repos.candidate.RecaptchaRepo;
import com.beeline.cc_question.services.candidate.RecaptchaService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {
    private final RecaptchaRepo recaptchaRepo;

    public RecaptchaServiceImpl(RecaptchaRepo recaptchaRepo) {
        this.recaptchaRepo = recaptchaRepo;
    }

    public boolean isValidCaptcha(String captcha) {
        Recaptcha secret = recaptchaByName("secret");
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String completeUrl = url + "?secret=" + secret.getKey() + "&response=" + captcha;
        CaptchaResponse resp = restTemplate.postForObject(completeUrl, null, CaptchaResponse.class);
        return resp.isSuccess();
    }

    @Override
    public Recaptcha recaptchaByName(String name) {
        return recaptchaRepo.findRecaptchaByName(name);
    }
}
