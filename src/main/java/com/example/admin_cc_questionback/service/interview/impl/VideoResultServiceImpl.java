package com.example.admin_cc_questionback.service.interview.impl;

import com.example.admin_cc_questionback.entities.interview.VideoResult;
import com.example.admin_cc_questionback.repository.interview.VideoResultRepo;
import com.example.admin_cc_questionback.service.interview.VideoResultService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideoResultServiceImpl implements VideoResultService {
    private final VideoResultRepo videoResultRepo;
    @Value("${server-config.upload-path}")
    private String UPLOADED_FOLDER;

    public VideoResultServiceImpl(VideoResultRepo videoResultRepo) {
        this.videoResultRepo = videoResultRepo;
    }

    @Override
    public byte[] sendFile(String fileName) throws IOException {
        Path path = Paths.get(UPLOADED_FOLDER).resolve(fileName);
        return Files.readAllBytes(path);
    }

    @Override
    public File findFileByFileName(String fileName) {
        File dir = new File(UPLOADED_FOLDER);
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files){
            if (file.getName().equals(fileName)){
                return file;
            }
        }
        return null;
    }

    @Override
    public VideoResult videoResultById(Long id) {
        return videoResultRepo.getVideoResultById(id);
    }

    @Override
    public boolean delete(Long id) {
        VideoResult videoResult = videoResultById(id);
        if (videoResult != null){
            videoResultRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String comment(String comment, Long id) {
        VideoResult videoResult = videoResultById(id);
        if (videoResult != null){
            videoResult.setComment(comment);
            videoResultRepo.save(videoResult);
            return comment;
        }
        return null;
    }

    @Override
    public boolean deleteFileById(Long id) {
        VideoResult videoResult = videoResultById(id);
        File file = findFileByFileName(videoResult.getVideoName());
        if (file != null){
            file.delete();
            videoResult.setVideoName("Removed");
            videoResultRepo.save(videoResult);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFileByName(String filename) {
        File file = findFileByFileName(filename);
        if (file != null){
            file.delete();
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<byte[]> download(Long id) throws IOException {
        VideoResult videoResult = videoResultRepo.getVideoResultById(id);
        String candidateName = videoResult.getCandidate().getName();
        String fileName = videoResult.getVideoName();
        String question = videoResult.getQuestion();
        return downloadById(id, candidateName, fileName, question);
    }

    @Override
    public ResponseEntity<byte[]> downloadById(Long id, String candidateName, String fileName, String question) throws IOException {
        ResponseEntity<byte[]> result;
        byte[] resource = sendFile(fileName);
        ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename(fileName + ".mp4").build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(contentDisposition);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(resource.length);
        result = new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
        return result;
    }


    @Override
    public List<File> all() {
        File dir = new File(UPLOADED_FOLDER);
        return List.of(dir.listFiles());
    }
}
