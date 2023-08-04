package com.example.admin_cc_questionback.service.interview;

import com.example.admin_cc_questionback.entities.interview.VideoResult;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface VideoResultService {
    byte[] sendFile(String fileName) throws IOException;
    File findFileByFileName(String fileName);
    VideoResult videoResultById(Long id);
    boolean delete(Long id);
    String comment(String comment, Long id);
    boolean deleteFileById(Long id);
    boolean deleteFileByName(String filename);
    ResponseEntity<byte[]> download(Long id) throws IOException;
    ResponseEntity<byte[]> downloadById(String fileName) throws IOException;
    List<File> all();
    Mono<Resource> getVideo(String title);
    Mono<Resource> streamVideo(Long id);
}
