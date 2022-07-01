package com.beeline.cc_question.controllers.interview;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.interview.Question;
import com.beeline.cc_question.services.interview.impl.QuestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.QUESTION)
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/forTest/{candidate_type_id}")
    public ResponseEntity<?> questionsForTest(@PathVariable(name = "candidate_type_id") Long candidateType_id) {
        List<Question> response = questionService.questionsForTest(candidateType_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/forVideo/{candidate_type_id}")
    public ResponseEntity<?> questionsForVideo(@PathVariable(name = "candidate_type_id") Long candidateType_id){
        List<Question> response = questionService.questionsForVideo(candidateType_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
