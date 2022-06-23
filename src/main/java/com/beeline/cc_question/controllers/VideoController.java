package com.beeline.cc_question.controllers;

import com.beeline.cc_question.dtos.VideoResultDto;
import com.beeline.cc_question.services.impl.VideoResultServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin
@Tag(name = "vcv", description = "VCV API")
@RestController
@RequestMapping(value = Url.API + Url.VIDEO)
public class VideoController {
private final VideoResultServiceImpl videoResultService;

    public VideoController(VideoResultServiceImpl videoResultService) {
        this.videoResultService = videoResultService;
    }


    @PostMapping("/upload/{candidate_id}")
    public ResponseEntity<?> upload(@RequestParam("multipartFile")MultipartFile multipartFile, @PathVariable(name = "candidate_id") Long candidate_id, @RequestParam String questionText, @RequestParam Long position) throws IOException {
        boolean response = videoResultService.uploadFile(multipartFile, candidate_id, questionText, position);
        return response
                ? new ResponseEntity<>("File was uploaded successfully", HttpStatus.OK)
                : new ResponseEntity<>("File hasn't got any file or couldn't upload it", HttpStatus.BAD_REQUEST);
    }
}
