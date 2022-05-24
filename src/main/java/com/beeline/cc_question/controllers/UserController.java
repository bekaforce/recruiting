package com.beeline.cc_question.controllers;

import com.beeline.cc_question.dtos.UserDto;
import com.beeline.cc_question.entities.User;
import com.beeline.cc_question.services.impl.GuestServiceImpl;
import com.beeline.cc_question.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "user", description = "User API")
@RestController
@RequestMapping(value = "/api/" + Url.USER)
public class UserController {
    private final GuestServiceImpl guestService;

    public UserController(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveGuest(@RequestBody UserDto userDto) {
        User response = guestService.addGuest(userDto.getName(), userDto.getPhoneNumber(), userDto.getEmail());
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }


}
