package com.example.admin_cc_questionback.controller.interview;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionVideoDto;
import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.service.interview.impl.QuestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.QUESTION)
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/saveToTest")
    public ResponseEntity<?> saveForTest(@RequestBody QuestionDto question){
        Question response = questionService.saveQuestionForTest(question);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/saveToVideo")
    public ResponseEntity<?> saveForVideo(@RequestBody QuestionVideoDto questionVideoDto){
        Question response = questionService.saveQuestionForVideo(questionVideoDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteFromTest")
    public ResponseEntity<?> deleteForTestById(@RequestParam Long question_id){
        boolean response = questionService.deleteQuestionForTest(question_id);
        return response
                ? new ResponseEntity<>("Question was deleted by id: " + question_id, HttpStatus.OK)
                : new ResponseEntity<>("Question was not found by id: " + question_id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteFromVideo")
    public ResponseEntity<?> deleteForVideoById(@RequestParam Long question_id){
        boolean response = questionService.deleteQuestionForVideo(question_id);
        return response
                ? new ResponseEntity<>("Question was deleted by id: " + question_id, HttpStatus.OK)
                : new ResponseEntity<>("Question was not found by id: " + question_id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/questionsForTest/{candidateType_id}")
    public ResponseEntity<?> questionsForTest(@PathVariable(name = "candidateType_id") Long candidateType_id){
        List<Question> response = questionService.getQuestionsForTest(candidateType_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/questionsForVideo/{candidateType_id}")
    public ResponseEntity<?> questionsForVideo(@PathVariable(name = "candidateType_id") Long candidateType_id){
        List<Question> response = questionService.getQuestionsForVideo(candidateType_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody QuestionUpdateDto questionUpdateDto){
        Question response = questionService.update(questionUpdateDto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
