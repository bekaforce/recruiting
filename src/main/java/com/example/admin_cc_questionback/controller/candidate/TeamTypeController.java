package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.TeamTypeDto;
import com.example.admin_cc_questionback.service.candidate.impl.TeamTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.TEAM_TYPE)
public class TeamTypeController {
    private final TeamTypeServiceImpl teamTypeService;

    public TeamTypeController(TeamTypeServiceImpl teamTypeService) {
        this.teamTypeService = teamTypeService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TeamTypeDto teamTypeDto){
        TeamType response = teamTypeService.save(teamTypeDto);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Try again", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        List<TeamType> response = teamTypeService.all();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        boolean response = teamTypeService.delete(id);
        return response
                ? new ResponseEntity<>("TeamType was removed by Id: " + id, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody TeamTypeDto teamTypeDto, @PathVariable(value = "id") Long id){
        TeamType response = teamTypeService.update(teamTypeDto, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

}
