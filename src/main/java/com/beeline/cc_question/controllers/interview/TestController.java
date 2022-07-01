package com.beeline.cc_question.controllers.interview;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.dtos.interview.TestDto;
import com.beeline.cc_question.entities.interview.Test;
import com.beeline.cc_question.services.interview.impl.TestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.TEST)
public class TestController {
    private final TestServiceImpl testService;

    public TestController(TestServiceImpl testService) {
        this.testService = testService;
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<?> save(@RequestBody List<TestDto> testDto, @PathVariable(value = "id") Long candidateId){
        String response = testService.save(testDto, candidateId);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/percentage/{candidate_id}")
    public ResponseEntity<?> percentage(@PathVariable(value = "candidate_id") Long candidate_id) {
        Long response = testService.percentage(candidate_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/stage/{candidate_id}")
    public ResponseEntity<?> stage(@PathVariable(value = "candidate_id") Long candidate_id) {
        String response = testService.stage(candidate_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
