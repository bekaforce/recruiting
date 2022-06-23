package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.VideoResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoResultService {
    boolean uploadFile(MultipartFile multipartFile, Long candidate_id, String questionText, Long position) throws IOException;
    Long position(Long candidate_id);
    VideoResult save(VideoResult videoResult);
}
