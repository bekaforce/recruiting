package com.beeline.cc_question.controllers;

import com.beeline.cc_question.entities.Question;
import com.beeline.cc_question.entities.Result;
import com.beeline.cc_question.entities.ResultInfo;
import com.beeline.cc_question.entities.VideoResult;
import com.beeline.cc_question.repos.QuestionRepo;
import com.beeline.cc_question.repos.ResultInfoRepo;
import com.beeline.cc_question.repos.ResultRepo;
import com.beeline.cc_question.repos.VideoResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    public QuestionRepo questionRepo;

    @Autowired
    public ResultRepo resultRepo;

    @Autowired
    public ResultInfoRepo resultInfoRepo;

    @Autowired
    public VideoResultRepo videoResultRepo;

    @Value("${upload.dir.location}")
    private String UPLOADED_FOLDER;

    @RequestMapping(method = RequestMethod.GET, value = "/getQuestions")
    public List<Question> getQuestions(){
        List<Question> questionList = questionRepo.findAll();
        return questionList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveResult")
    public ResponseEntity<String> saveResult(@RequestBody ResultInfo resultInfoSample) {
        ResultInfo savedResultInfo = resultInfoRepo.findByUserId(resultInfoSample.getUserId());

        if (savedResultInfo == null) {
            savedResultInfo  = resultInfoRepo.save(resultInfoSample);
        }

        for (Result result : resultInfoSample.getResults()) {
            result.setResultInfo(savedResultInfo);
            resultRepo.save(result);
        }
        return new ResponseEntity<>("response is successful", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveVideo")
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file,
                                            @RequestParam("userId") String userId) throws SQLException {
        String responseBody = null;
        ResultInfo resultInfo = resultInfoRepo.findByUserId(userId);

        if (resultInfo == null ) {
            return new ResponseEntity<>("There is no such userId", HttpStatus.NOT_FOUND);
        }

        try {
            byte[] bytes = file.getBytes();
            Files.createDirectories(Paths.get(UPLOADED_FOLDER));
            Path path = Paths.get(UPLOADED_FOLDER).resolve(userId + ".mp4");
            Files.write(path, bytes);
            responseBody = "\'" + userId + "\' is successfully uploaded";
            videoResultRepo.save(new VideoResult(path.toString(),resultInfo));
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
