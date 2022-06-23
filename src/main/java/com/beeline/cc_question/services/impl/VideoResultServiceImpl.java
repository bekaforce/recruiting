package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.QuestionType;
import com.beeline.cc_question.entities.VideoResult;
import com.beeline.cc_question.repos.VideoResultRepo;
import com.beeline.cc_question.services.VideoResultService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class VideoResultServiceImpl implements VideoResultService {
    private final VideoResultRepo videoResultRepo;
    private final CandidateServiceImpl candidateService;
    private final QuestionServiceImpl questionService;

    @Value("${server-config.upload-path}")
    private String UPLOADED_FOLDER;

    public VideoResultServiceImpl(VideoResultRepo videoResultRepo, CandidateServiceImpl candidateService, QuestionServiceImpl questionService) {
        this.videoResultRepo = videoResultRepo;
        this.candidateService = candidateService;
        this.questionService = questionService;
    }

    @Override
    public boolean uploadFile(MultipartFile multipartFile, Long candidate_id, String questionText, Long position) throws IOException {
        if (multipartFile != null && !multipartFile.toString().equals("")){
            File uploadDir = new File(UPLOADED_FOLDER);
            String uuidFile = UUID.randomUUID().toString();
            String filename = uuidFile + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadDir + "/" + filename));
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
            Candidate candidate = candidateService.candidateById(candidate_id);
            VideoResult videoResult = new VideoResult(filename, now, candidate, questionText, null, position);
            save(videoResult);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Long position(Long candidate_id) {
        Long position = videoResultRepo.position(candidate_id);
        if (position != null){
            return position;
        }
        return 0L;
    }

    @Override
    public VideoResult save(VideoResult videoResult) {
        Long candidateType_id = videoResult.getCandidate().getCandidateType().getId();
        Long maxPosition = questionService.maxPosition(QuestionType.VIDEO.toString(), candidateType_id);
        if (videoResult.getPosition() >= maxPosition){
            candidateService.setStage(videoResult.getCandidate(), "completed");
        }
        return videoResultRepo.save(videoResult);
    }
}
