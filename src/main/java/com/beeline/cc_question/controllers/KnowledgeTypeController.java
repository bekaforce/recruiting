package com.beeline.cc_question.controllers;

import com.beeline.cc_question.entities.KnowledgeType;
import com.beeline.cc_question.services.impl.KnowledgeTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.KNOWLEDGETYPE)
public class KnowledgeTypeController {
    private final KnowledgeTypeServiceImpl knowledgeTypeService;

    public KnowledgeTypeController(KnowledgeTypeServiceImpl knowledgeTypeService) {
        this.knowledgeTypeService = knowledgeTypeService;
    }

    @GetMapping("/all/{candidate_type_id}")
    public ResponseEntity<?> allByCandidateType(@PathVariable(name = "candidate_type_id") Long candidateType){
        List<KnowledgeType> response = knowledgeTypeService.allByCandidateType(candidateType);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
