package com.beeline.cc_question.controllers.user;


import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.services.candidate.impl.GuestServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "user", description = "User API")
@RestController
@RequestMapping(value = Url.API + Url.USER)
public class UserController {
    private final GuestServiceImpl guestService;

    public UserController(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/setPassword")
    public ResponseEntity<?> setPassword(@RequestParam String password, @RequestParam Long id) {
        Long response = guestService.setPassword(password, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Password should be correct", HttpStatus.BAD_REQUEST);
    }
}
