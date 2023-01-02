package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.candidate.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;
import com.example.admin_cc_questionback.entities.dtos.LevelUpdateDto;
import com.example.admin_cc_questionback.service.candidate.impl.LevelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = Url.ADMIN + Url.API + Url.LEVEL)
public class LevelController {
    private final LevelServiceImpl levelService;

    public LevelController(LevelServiceImpl levelService) {
        this.levelService = levelService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LevelDto levelDto){
        Level response = levelService.save(levelDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        boolean response = levelService.delete(id);
        return response
                ? new ResponseEntity<>("Level was deleted by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody LevelUpdateDto levelUpdateDto){
        Level response = levelService.update(levelUpdateDto);
        return response != null
                ? new ResponseEntity<>("Level was updated by id: " + levelUpdateDto.getId(), HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }



}
