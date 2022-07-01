package com.example.admin_cc_questionback.controller;

import com.example.admin_cc_questionback.entities.Hooligan;
import com.example.admin_cc_questionback.entities.dtos.HooliganDto;
import com.example.admin_cc_questionback.service.impl.HooliganServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.API + Url.HOOLIGAN)
public class HooliganController {
    private final HooliganServiceImpl hooliganService;

    public HooliganController(HooliganServiceImpl hooliganService) {
        this.hooliganService = hooliganService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        boolean response = hooliganService.delete(id);
        return response
                ? new ResponseEntity<>("Hooligan was removed by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HooliganDto hooliganDto){
        Hooligan response = hooliganService.save(hooliganDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try Again", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HooliganDto hooliganDto){
        Hooligan response = hooliganService.update(id, hooliganDto);
        return response != null
                ? new ResponseEntity<>("Hooligan was updated by id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        List<Hooligan> response = hooliganService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
