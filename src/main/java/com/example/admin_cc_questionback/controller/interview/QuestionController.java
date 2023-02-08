package com.example.admin_cc_questionback.controller.interview;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.dtos.QuestionDto;
import com.example.admin_cc_questionback.entities.dtos.QuestionInterviewDto;
import com.example.admin_cc_questionback.entities.interview.Question;
import com.example.admin_cc_questionback.entities.dtos.QuestionUpdateDto;
import com.example.admin_cc_questionback.service.interview.impl.QuestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.QUESTION)
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/saveToTest")
    public ResponseEntity<?> saveForTest(@RequestBody QuestionDto question){
        Question response = questionService.saveQuestionForTest(question);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/saveToInterview")
    public ResponseEntity<?> saveForInterview(@RequestBody QuestionInterviewDto questionInterviewDto){
        Question response = questionService.saveQuestionForInterview(questionInterviewDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @DeleteMapping("/deleteFromTest")
    public ResponseEntity<?> deleteForTestById(@RequestParam Long question_id){
        boolean response = questionService.deleteQuestionForTest(question_id);
        return response
                ? new ResponseEntity<>("Question was deleted by id: " + question_id, HttpStatus.OK)
                : new ResponseEntity<>("Question was not found by id: " + question_id, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @DeleteMapping("/deleteFromInterview")
    public ResponseEntity<?> deleteForInterviewById(@RequestParam Long question_id){
        boolean response = questionService.deleteQuestionForInterview(question_id);
        return response
                ? new ResponseEntity<>("Question was deleted by id: " + question_id, HttpStatus.OK)
                : new ResponseEntity<>("Question was not found by id: " + question_id, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_Obuchenie')")
    @GetMapping("/questionsForTest/{candidateType_id}")
    public ResponseEntity<?> questionsForTest(@PathVariable(name = "candidateType_id") Long candidateType_id){
        List<Question> response = questionService.getQuestionsForTest(candidateType_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_Obuchenie')")
    @GetMapping("/questionsForInterview/{candidateType_id}")
    public ResponseEntity<?> questionsForInterview(@PathVariable(name = "candidateType_id") Long candidateType_id){
        List<Question> response = questionService.getQuestionsForInterview(candidateType_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody QuestionUpdateDto questionUpdateDto){
        Question response = questionService.update(questionUpdateDto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
