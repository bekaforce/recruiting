package com.beeline.cc_question.controllers;

import com.beeline.cc_question.entities.*;
import com.beeline.cc_question.repos.*;
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
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    public QuestionRepo questionRepo;

    @Autowired
    public ResultRepo resultRepo;

    @Autowired
    public UserInfoRepo userInfoRepo;

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
    public ResponseEntity<String> saveResult(@RequestBody UserInfo userInfoSample) {
        UserInfo savedUserInfo = userInfoRepo.findByUserId(userInfoSample.getUserId());

        if (savedUserInfo == null) {
            savedUserInfo = userInfoRepo.save(userInfoSample);
        }

        for (Result result : userInfoSample.getResults()) {
            result.setUserInfo(savedUserInfo);
            resultRepo.save(result);
        }
        return new ResponseEntity<>("response is successful", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveVideo")
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file,
                                            @RequestParam("userId") String userId) throws SQLException {
        UserInfo userInfo = userInfoRepo.findByUserId(userId);

        if (userInfo == null) {
            return new ResponseEntity<>("There is no such userId", HttpStatus.NOT_FOUND);
        }

        try {
            byte[] bytes = file.getBytes();
            Files.createDirectories(Paths.get(UPLOADED_FOLDER));
            Path path = Paths.get(UPLOADED_FOLDER).resolve(userId + ".mp4");
            Files.write(path, bytes);
            videoResultRepo.save(new VideoResult(path.toString(), userInfo));
            return new ResponseEntity<>("\'" + userId + "\' is successfully uploaded", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getResults")
    public List<UserInfo> getAllResults() {
        List<UserInfo> allResults = userInfoRepo.findAll();
        for (UserInfo userInfo : allResults) {
            Optional<VideoResult> videoResult = videoResultRepo.findByUserInfo(userInfo);
            if (!videoResult.isEmpty()) {
                userInfo.setVideo(videoResult.get().getLocation());
            }
        }
        return allResults;
    }
}
