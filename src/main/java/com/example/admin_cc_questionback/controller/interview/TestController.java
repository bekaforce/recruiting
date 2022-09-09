package com.example.admin_cc_questionback.controller.interview;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.service.interview.impl.TestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.TEST)
public class TestController {
    private final TestServiceImpl testService;

    public TestController(TestServiceImpl testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/percentage/{candidate_id}")
    public ResponseEntity<?> percentage(@PathVariable(value = "candidate_id") Long candidate_id) {
        Long response = testService.percentage(candidate_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
