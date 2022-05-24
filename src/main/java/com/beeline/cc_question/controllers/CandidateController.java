package com.beeline.cc_question.controllers;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.services.impl.CandidateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/" + Url.CANDIDATE)
public class CandidateController {
    private final CandidateServiceImpl candidateService;

    public CandidateController(CandidateServiceImpl candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/saveCandidate")
    public ResponseEntity<?> saveCandidate(@RequestBody CandidateDto candidateDto){
        Candidate response = candidateService.saveCandidate(candidateDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
