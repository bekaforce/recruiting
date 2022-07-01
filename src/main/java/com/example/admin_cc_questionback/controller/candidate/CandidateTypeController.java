package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;
import com.example.admin_cc_questionback.service.candidate.impl.CandidateTypeServiceImpl;
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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CandidateTypeDto candidateType){
        CandidateType response = candidateTypeService.save(candidateType);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        List<CandidateType> response = candidateTypeService.all();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean response = candidateTypeService.delete(id);
        return response
                ? new ResponseEntity<>("CandidateType was removed by Id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody CandidateTypeDto candidateTypeDto, @PathVariable Long id){
        CandidateType response = candidateTypeService.update(candidateTypeDto, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
