package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.Knowledge;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeDto;
import com.example.admin_cc_questionback.service.impl.KnowledgeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        boolean response = knowledgeService.delete(id);
        return response
                ? new ResponseEntity<>("Knowledge was removed by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody KnowledgeDto knowledgeDto){
        Knowledge response = knowledgeService.save(knowledgeDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try Again", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, String name){
        Knowledge response = knowledgeService.update(id, name);
        return response != null
                ? new ResponseEntity<>("Knowledge was updated by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
