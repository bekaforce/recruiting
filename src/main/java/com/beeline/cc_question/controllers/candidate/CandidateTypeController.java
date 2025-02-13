package com.beeline.cc_question.controllers.candidate;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.candidate.CandidateType;
import com.beeline.cc_question.entities.dtos.candidate.CandidateTypeDto;
import com.beeline.cc_question.services.candidate.impl.CandidateTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.CANDIDATETYPE)
public class CandidateTypeController {
    private final CandidateTypeServiceImpl candidateTypeService;

    public CandidateTypeController(CandidateTypeServiceImpl candidateTypeService) {
        this.candidateTypeService = candidateTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> allCandidateType(){
        List<CandidateType> response = candidateTypeService.all();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allActiveAndExternal")
    public ResponseEntity<?> allActiveAndExternal(){
        List<CandidateType> response = candidateTypeService.allActiveAndExternal();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allActiveAndInternal")
    public ResponseEntity<?> allActiveAndInternal(){
        List<CandidateType> response = candidateTypeService.allActiveAndInternal();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("candidateTypeById/{id}")
    public ResponseEntity<?> candidateTypeById(@PathVariable(value = "id") Long id){
        CandidateTypeDto response = candidateTypeService.candidateTypeDtoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
