package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.candidate.KnowledgeType;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeTypeDto;
import com.example.admin_cc_questionback.service.candidate.impl.KnowledgeTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.KNOWLEDGETYPE)
public class KnowledgeTypeController {
    private final KnowledgeTypeServiceImpl knowledgeTypeService;

    public KnowledgeTypeController(KnowledgeTypeServiceImpl knowledgeTypeService) {
        this.knowledgeTypeService = knowledgeTypeService;
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        boolean response = knowledgeTypeService.delete(id);
        return response
                ? new ResponseEntity<>("KnowledgeType was removed by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody KnowledgeTypeDto knowledgeTypeDto){
        KnowledgeType response = knowledgeTypeService.save(knowledgeTypeDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try Again", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, String name){
        KnowledgeType response = knowledgeTypeService.update(id, name);
        return response != null
                ? new ResponseEntity<>("KnowledgeType was updated by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @GetMapping("/all/{candidate_type_id}")
    public ResponseEntity<?> allByCandidateType(@PathVariable(name = "candidate_type_id") Long candidateType){
        List<KnowledgeType> response = knowledgeTypeService.allByCandidateType(candidateType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
