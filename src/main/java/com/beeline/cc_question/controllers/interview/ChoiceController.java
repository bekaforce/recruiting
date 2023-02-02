package com.beeline.cc_question.controllers.interview;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.services.interview.impl.ChoiceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(Url.API + Url.CHOICE)
public class ChoiceController {
    private final ChoiceServiceImpl choiceService;

    public ChoiceController(ChoiceServiceImpl choiceService) {
        this.choiceService = choiceService;
    }

    @PostMapping(Url.BETWEEN + Url.ESSAYANDVIDEO + "/{candidate_id}")
    public ResponseEntity<?> chooseTypeOfInterview(@RequestParam String interview, @PathVariable(value = "candidate_id") Long candidate_id){
        String response = choiceService.chooseEssayOrVideo(candidate_id, interview);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(Url.ALL + Url.ESSAYANDVIDEO)
    public ResponseEntity<?> getTypesOfInterview(){
        List<String> response = choiceService.getEssayAndVideo();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
