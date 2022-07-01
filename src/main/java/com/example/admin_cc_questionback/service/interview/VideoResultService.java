package com.example.admin_cc_questionback.service.interview;

import com.example.admin_cc_questionback.entities.interview.VideoResult;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface VideoResultService {
    byte[] sendFile(String fileName) throws IOException;
    File findFileByFileName(String fileName);
    VideoResult videoResultById(Long id);
    boolean delete(Long id);
    String comment(String comment, Long id);
    boolean deleteFileById(Long id);
    boolean deleteFileByName(String filename);
    ResponseEntity<byte[]> download(Long id) throws IOException;
    ResponseEntity<byte[]> downloadById(Long id, String candidateName, String fileName, String question) throws IOException;
    List<File> all();
}
