package com.beeline.cc_question.controllers;

import com.beeline.cc_question.dtos.TestDto;
import com.beeline.cc_question.entities.Test;
import com.beeline.cc_question.services.impl.TestServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/" + Url.TEST)
public class TestController {
    private final TestServiceImpl testService;

    public TestController(TestServiceImpl testService) {
        this.testService = testService;
    }

    @PostMapping("/saveTest/{id}")
    public ResponseEntity<?> saveQuestionForTest(@RequestBody List<TestDto> testDtos, @PathVariable(value = "id") Long candidateId){
        List<Test> response = testService.saveTest(testDtos, candidateId);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }
}
