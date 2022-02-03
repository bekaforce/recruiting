package com.beeline.cc_question.controllers;

import com.beeline.cc_question.entities.Question;
import com.beeline.cc_question.entities.Result;
import com.beeline.cc_question.entities.ResultInfo;
import com.beeline.cc_question.repos.QuestionRepo;
import com.beeline.cc_question.repos.ResultInfoRepo;
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

    @Autowired
    public ResultInfoRepo resultInfoRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/getQuestions")
    public List<Question> getQuestions(){
        List<Question> questionList = questionRepo.findAll();
        return questionList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveResult")
    public ResponseEntity<String> saveResult(@RequestBody ResultInfo resultInfo) {
        ResultInfo savedResultInfo = resultInfoRepo.save(resultInfo);

        for (Result result : resultInfo.getResults()) {
            result.setResultInfo(savedResultInfo);
            resultRepo.save(result);
        }
        return new ResponseEntity<>("response is successful", HttpStatus.OK);
    }
}
