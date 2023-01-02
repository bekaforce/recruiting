package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.service.candidate.impl.CandidateServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.CANDIDATE)
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

    @GetMapping("/allCandidates/{candidateType_id}")
    public ResponseEntity<?> allCandidatesByType(@PathVariable(value = "candidateType_id") Long id){
        List<CandidateDto> response = candidateService.all(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allFailedCandidates/{candidateType_id}")
    public ResponseEntity<?> allFailedCandidatesByType(@PathVariable(value = "candidateType_id") Long id){
        List<CandidateDto> response = candidateService.allFailed(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allCandidatesOnVideoByType/{candidateType_id}")
    public ResponseEntity<?> allCandidatesOnVideoByType(@PathVariable(value = "candidateType_id") Long id){
        List<CandidateDto> response = candidateService.allOnVideo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_Obuchenie')")
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> comment(@PathVariable(value = "id") Long id, @RequestParam String comment){
        String response = candidateService.setComment(id, comment);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/setStatus/{id}")
    public ResponseEntity<?> setStatus(@PathVariable(value = "id") Long id, @RequestParam String status){
        String response = candidateService.setStatus(status, id);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/setInvitationDate/{id}")
    public ResponseEntity<?> setInvitationDate(@PathVariable(value = "id") Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate invitationDate){
        LocalDate response = candidateService.setInvitationDate(invitationDate, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/setGender/{id}")
    public ResponseEntity<?> setGender(@PathVariable(value = "id") Long id, @RequestParam String gender){
        boolean response = candidateService.setGender(id, gender);
        return response
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
