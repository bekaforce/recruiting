package com.beeline.cc_question.controllers.candidate;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.candidate.Knowledge;
import com.beeline.cc_question.services.candidate.impl.KnowledgeServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.KNOWLEDGE)
public class KnowledgeController {
    private final KnowledgeServiceImpl knowledgeService;

    public KnowledgeController(KnowledgeServiceImpl knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/all/{knowledge_type_id}")
    public ResponseEntity<?> allByKnowledgeType(@PathVariable(name = "knowledge_type_id") Long knowledgeType){
        List<Knowledge> response = knowledgeService.allByKnowledgeType(knowledgeType);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
