package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.Message;
import com.example.admin_cc_questionback.service.impl.MessageServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = Url.MESSAGE)
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Long id){
        Message response = messageService.getMessage(id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @Hidden
    @PostMapping("/create")
    public ResponseEntity<?> createMessage(@RequestParam String text){
        Message response = messageService.createMessage(text);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestParam String text){
        Message response = messageService.updateMessage(id, text);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
