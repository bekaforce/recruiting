package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.Question;
import com.example.admin_cc_questionback.service.impl.QuestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.QUESTION)
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/saveToTest")
    public ResponseEntity<?> saveQuestionForTest(@RequestParam String questionText){
        Question response = questionService.saveQuestionForTest(questionText);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/saveToVideo")
    public ResponseEntity<?> saveQuestionForVideo(@RequestBody QuestionDto question){
        Question response = questionService.saveQuestionForVideo(question.getQuestionText());
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteFromTest")
    public ResponseEntity<?> deleteQuestionForTestById(@RequestParam Long question_id){
        boolean response = questionService.deleteQuestionForTest(question_id);
        return response
                ? new ResponseEntity<>("Question was deleted by id: " + question_id, HttpStatus.OK)
                : new ResponseEntity<>("Question was not found by id: " + question_id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteFromVideo")
    public ResponseEntity<?> deleteQuestionForVideoById(@RequestParam Long question_id){
        boolean response = questionService.deleteQuestionForVideo(question_id);
        return response
                ? new ResponseEntity<>("Question was deleted by id: " + question_id, HttpStatus.OK)
                : new ResponseEntity<>("Question was not found by id: " + question_id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/questionsForTest")
    public ResponseEntity<?> questionsForTest(){
        List<Question> response = questionService.getQuestionsForTest();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/questionsForVideo")
    public ResponseEntity<?> questionsForVideo(){
        List<Question> response = questionService.getQuestionsForVideo();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDto questionDto, @PathVariable Long id){
        Question response = questionService.updateQuestion(questionDto.getQuestionText(), id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
