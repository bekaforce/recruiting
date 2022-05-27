package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.Candidate;
import com.example.admin_cc_questionback.service.impl.CandidateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.CANDIDATE)
public class CandidateController {
    private final CandidateServiceImpl candidateService;

    public CandidateController(CandidateServiceImpl candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCandidate(@PathVariable Long id){
        Candidate response = candidateService.candidateById(id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allOperators")
    public ResponseEntity<?> allOperators(){
        List<CandidateDto> response = candidateService.operators();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
