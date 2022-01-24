package com.beeline.cc_question.controllers;

import com.beeline.cc_question.entities.Answer;
import com.beeline.cc_question.entities.Question;
import com.beeline.cc_question.entities.Result;
import com.beeline.cc_question.repos.QuestionRepo;
import com.beeline.cc_question.repos.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    public QuestionRepo questionRepo;

    @Autowired
    public ResultRepo resultRepo;


    @RequestMapping(method = RequestMethod.GET, value = "/getQuestions")
    public List<Question> getQuestions(){
        List<Question> questionList = questionRepo.findAll();
        return questionList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveResult")
    public ResponseEntity<String> saveResult(@RequestBody List<Result> results) {
        for (Result result : results) {
            for (Answer answer : result.getAnswers()) {
                result.setAnswer(answer);
                Result eachResult = new Result(result.getQuestion(), result.getAnswer());
                resultRepo.save(eachResult);
            }
        }
        return new ResponseEntity<>("response is successful", HttpStatus.OK);
    }
}
