package com.beeline.cc_question.controllers;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.services.impl.CandidateServiceImpl;
import com.beeline.cc_question.services.impl.GuestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.CANDIDATE)
public class CandidateController {
    private final CandidateServiceImpl candidateService;
    private final GuestServiceImpl guestService;

    public CandidateController(CandidateServiceImpl candidateService, GuestServiceImpl guestService) {
        this.candidateService = candidateService;
        this.guestService = guestService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CandidateDto candidateDto){
       Candidate response = guestService.add(candidateDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        List<Candidate> candidates = candidateService.getAll();
        return candidates != null
                ? new ResponseEntity<>(candidates, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
