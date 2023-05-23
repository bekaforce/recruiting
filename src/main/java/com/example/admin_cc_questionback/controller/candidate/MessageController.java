package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.candidate.Message;
import com.example.admin_cc_questionback.service.candidate.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.MESSAGE)
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Long id){
        Message response = messageService.messageById(id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @GetMapping("/fullMessage")
    public ResponseEntity<?> fullMessage(){
        List<Message> response = messageService.message();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/update")
    public ResponseEntity<?> updateMessage(@RequestBody Message updatedMessage){
        Message response = messageService.update(updatedMessage);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}