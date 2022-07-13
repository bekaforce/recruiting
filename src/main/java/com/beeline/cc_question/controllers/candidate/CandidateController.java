package com.beeline.cc_question.controllers.candidate;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;
import com.beeline.cc_question.services.candidate.impl.GuestServiceImpl;
import com.beeline.cc_question.services.candidate.impl.recaptcha.RecaptchaV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.CANDIDATE)
public class CandidateController {
    private final GuestServiceImpl guestService;
    private final RecaptchaV2 recaptchaV2;

    public CandidateController(GuestServiceImpl guestService, RecaptchaV2 recaptchaV2) {
        this.guestService = guestService;
        this.recaptchaV2 = recaptchaV2;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CandidateDto candidateDto){
        Candidate response = null;
        if (recaptchaV2.isValidCaptcha(candidateDto.getCaptcha())){
            response = guestService.add(candidateDto);
        }
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/success/{candidate_id}")
    public ResponseEntity<?> all(@PathVariable(name = "candidate_id") Long candidate_id){
        SuccessDto success = guestService.success(candidate_id);
        return success != null
                ? new ResponseEntity<>(success, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
