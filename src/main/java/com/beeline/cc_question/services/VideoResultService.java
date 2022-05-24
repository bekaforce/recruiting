package com.beeline.cc_question.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoResultService {
    boolean uploadFile(MultipartFile multipartFile, Long candidate_id, String questionText) throws IOException;
}
