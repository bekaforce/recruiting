package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.Message;
import com.example.admin_cc_questionback.service.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.MESSAGE)
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Long id){
        Message response = messageService.messageById(id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fullMessage")
    public ResponseEntity<?> fullMessage(){
        List<Message> response = messageService.message();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String text){
        Message response = messageService.save(text);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMessage(@RequestBody Message message){
        Message response = messageService.update(message);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
