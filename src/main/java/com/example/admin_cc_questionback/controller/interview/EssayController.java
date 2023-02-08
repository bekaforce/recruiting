package com.example.admin_cc_questionback.controller.interview;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.service.interview.impl.EssayServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.ESSAY)
public class EssayController {
    private final EssayServiceImpl essayService;

    public EssayController(EssayServiceImpl essayService) {
        this.essayService = essayService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/comment/{id}")
    public ResponseEntity<?> comment(@PathVariable(value = "id") Long id, @RequestParam String comment){
        String response = essayService.comment(comment, id);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
