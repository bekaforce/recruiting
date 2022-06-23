package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;
import com.example.admin_cc_questionback.service.impl.LevelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.LEVEL)
public class LevelController {
    private final LevelServiceImpl levelService;

    public LevelController(LevelServiceImpl levelService) {
        this.levelService = levelService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LevelDto levelDto){
        Level response = levelService.save(levelDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        boolean response = levelService.delete(id);
        return response
                ? new ResponseEntity<>("Level was deleted by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestParam String name){
        Level response = levelService.update(id, name);
        return response != null
                ? new ResponseEntity<>("Level was updated by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }



}
