package com.beeline.cc_question.controllers.candidate;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.candidate.Feedback;
import com.beeline.cc_question.entities.dtos.candidate.FeedbackDto;
import com.beeline.cc_question.services.candidate.impl.FeedbackServiceImpl;
import com.beeline.cc_question.services.candidate.impl.recaptcha.RecaptchaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.FEEDBACK)
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;
    private final RecaptchaServiceImpl recaptchaService;

    public FeedbackController(FeedbackServiceImpl feedbackService, RecaptchaServiceImpl recaptchaService) {
        this.feedbackService = feedbackService;
        this.recaptchaService = recaptchaService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody FeedbackDto feedbackDto){
        Feedback response = null;
        if (recaptchaService.isValidCaptcha(feedbackDto.getCaptcha())){
            response = feedbackService.save(feedbackDto);
        }
        return response != null
                ? new ResponseEntity<>("saved", HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
