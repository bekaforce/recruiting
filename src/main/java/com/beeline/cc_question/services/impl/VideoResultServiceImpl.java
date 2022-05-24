package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.VideoResult;
import com.beeline.cc_question.repos.VideoResultRepo;
import com.beeline.cc_question.services.VideoResultService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VideoResultServiceImpl implements VideoResultService {
    private final VideoResultRepo videoResultRepo;
    private final CandidateServiceImpl candidateService;
    @Value("${server-config.upload-path}")
    private String UPLOADED_FOLDER;

    public VideoResultServiceImpl(VideoResultRepo videoResultRepo, CandidateServiceImpl candidateService, QuestionServiceImpl questionService) {
        this.videoResultRepo = videoResultRepo;
        this.candidateService = candidateService;
    }

    @Override
    public boolean uploadFile(MultipartFile multipartFile, Long candidate_id, String questionText) throws IOException {
        if (multipartFile != null && !multipartFile.toString().equals("")){
            File uploadDir = new File(UPLOADED_FOLDER);
            String uuidFile = UUID.randomUUID().toString();
            String filename = uuidFile + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadDir + "/" + filename));
            LocalDateTime now = LocalDateTime.now();
            Candidate candidate = candidateService.getCandidateById(candidate_id);
            VideoResult videoResult = new VideoResult(filename, now, candidate, questionText, null);
            videoResultRepo.save(videoResult);
            return true;
        }
        else {
            return false;
        }
    }
}
