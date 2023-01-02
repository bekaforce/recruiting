package com.example.admin_cc_questionback.controller.interview;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.dtos.AnswerDto;
import com.example.admin_cc_questionback.entities.dtos.ContentDto;
import com.example.admin_cc_questionback.entities.interview.Answer;
import com.example.admin_cc_questionback.service.interview.impl.AnswerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.ANSWER)
public class AnswerController {
    private final AnswerServiceImpl answerService;

    public AnswerController(AnswerServiceImpl answerService) {
        this.answerService = answerService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveAnswer(@RequestBody AnswerDto answerDto){
        Answer response = answerService.save(answerDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @DeleteMapping("/delete/{answer_id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long answer_id){
        boolean response = answerService.delete(answer_id);
        return response
                ? new ResponseEntity<>("Answer was deleted by id: " + answer_id, HttpStatus.OK)
                : new ResponseEntity<>("Answer was not found by id: " + answer_id, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAnswer(@RequestBody ContentDto content, @PathVariable Long id){
        Answer response = answerService.update(content, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
