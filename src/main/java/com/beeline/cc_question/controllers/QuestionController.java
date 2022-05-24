package com.beeline.cc_question.controllers;

import com.beeline.cc_question.entities.Question;
import com.beeline.cc_question.services.impl.QuestionServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/" + Url.QUESTION)
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/forTest")
    public ResponseEntity<?> getQuestionsForTest(){
        List<Question> response = questionService.getQuestionsForTest();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/forVideo")
    public ResponseEntity<?> getQuestionsForVideo(){
        List<Question> response = questionService.getQuestionsForVideo();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
