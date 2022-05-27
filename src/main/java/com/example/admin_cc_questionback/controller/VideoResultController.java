package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.service.impl.VideoResultServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = Url.VIDEO)
public class VideoResultController {
    private final VideoResultServiceImpl videoResultService;

    public VideoResultController(VideoResultServiceImpl videoResultService) {
        this.videoResultService = videoResultService;
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        boolean response = videoResultService.deleteById(id);
        return response
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<?> downloadById(@PathVariable(value = "id") Long id, HttpServletResponse httpResponse) throws IOException {
        httpResponse.setStatus(HttpStatus.OK.value());
        ResponseEntity<byte[]> response = videoResultService.download(id);
        return response != null
                ? response
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping ("/comment/{id}")
    public ResponseEntity<?> comment(@PathVariable(value = "id") Long id, @RequestParam String comment){
        String response = videoResultService.comment(comment, id);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/deleteFileById/{id}")
    public ResponseEntity<?> deleteFileById(@PathVariable(value = "id") Long id){
        boolean response = videoResultService.deleteFileById(id);
        return response
                ? new ResponseEntity<>("File was deleted by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteFileByName")
    public ResponseEntity<?> deleteFileByName(@RequestParam String filename){
        boolean response = videoResultService.deleteFileByName(filename);
        return response
                ? new ResponseEntity<>("File was deleted by filename: " + filename, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping ("/all")
    public ResponseEntity<?> all(){
        List<File> response = videoResultService.all();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
