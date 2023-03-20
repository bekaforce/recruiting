package com.beeline.cc_question.controllers.candidate;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;
import com.beeline.cc_question.services.candidate.impl.GuestServiceImpl;
import com.beeline.cc_question.services.candidate.impl.recaptcha.RecaptchaServiceImpl;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.CANDIDATE)
public class CandidateController {
    private final GuestServiceImpl guestService;
    private final RecaptchaServiceImpl recaptchaService;

    public CandidateController(GuestServiceImpl guestService, RecaptchaServiceImpl recaptchaService) {
        this.guestService = guestService;
        this.recaptchaService = recaptchaService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CandidateDto candidateDto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, DecoderException {
        Candidate response = null;
        //if (recaptchaService.isValidCaptcha(candidateDto.getRecaptcha())){
            response = guestService.add(candidateDto);
        //}
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/success/{candidate_id}")
    public ResponseEntity<?> success(@PathVariable(name = "candidate_id") Long candidate_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SuccessDto response = guestService.success(candidate_id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
