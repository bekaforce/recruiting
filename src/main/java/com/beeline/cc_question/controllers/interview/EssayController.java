package com.beeline.cc_question.controllers.interview;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.dtos.interview.EssayDto;
import com.beeline.cc_question.services.interview.impl.EssayServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(Url.API + Url.ESSAY)
public class EssayController {
    private final EssayServiceImpl essayService;

    public EssayController(EssayServiceImpl essayService) {
        this.essayService = essayService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EssayDto essayDto){
        boolean response = essayService.save(essayDto, essayDto.getCandidate_id());
        return response
                ? new ResponseEntity<>("Essay was saved successfully", HttpStatus.OK)
                : new ResponseEntity<>("Essay couldn't be saved", HttpStatus.BAD_REQUEST);
    }


}
