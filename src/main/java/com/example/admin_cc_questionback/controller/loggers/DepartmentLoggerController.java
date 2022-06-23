package com.example.admin_cc_questionback.controller.loggers;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.loggers.DepartmentLogger;
import com.example.admin_cc_questionback.service.impl.loggers.DepartmentLoggerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + LoggerUrl.LOG)
public class DepartmentLoggerController {
    private final DepartmentLoggerServiceImpl departmentLoggerService;

    public DepartmentLoggerController(DepartmentLoggerServiceImpl departmentLoggerService) {
        this.departmentLoggerService = departmentLoggerService;
    }

    @GetMapping("/logs")
    public ResponseEntity<?> logs(){
        List<DepartmentLogger> response = departmentLoggerService.all();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
