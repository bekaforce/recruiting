package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.Answer;
import com.example.admin_cc_questionback.service.impl.AnswerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.ANSWER)
public class AnswerController {
    private final AnswerServiceImpl answerService;

    public AnswerController(AnswerServiceImpl answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAnswer(@RequestBody AnswerDto answerDto){
        Answer response = answerService.save(answerDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{answer_id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long answer_id){
        boolean response = answerService.delete(answer_id);
        return response
                ? new ResponseEntity<>("Answer was deleted by id: " + answer_id, HttpStatus.OK)
                : new ResponseEntity<>("Answer was not found by id: " + answer_id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAnswer(@RequestBody ContentDto content, @PathVariable Long id){
        Answer response = answerService.update(content, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
