package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.loggers.DepartmentLogger;
import com.example.admin_cc_questionback.repository.loggers.DepartmentLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.DepartmentLoggerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentLoggerServiceImpl implements DepartmentLoggerService {
    private final DepartmentLoggerRepo departmentLoggerRepo;
    private final LoggerService loggerService;

    public DepartmentLoggerServiceImpl(DepartmentLoggerRepo departmentLoggerRepo, LoggerService loggerService) {
        this.departmentLoggerRepo = departmentLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public List<DepartmentLogger> all() {
        return departmentLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(DepartmentLogger::getId).reversed()).collect(Collectors.toList());
    }

    @Override
    public DepartmentLogger save(String before, String after, String status) {
        DepartmentLogger departmentLogger = new DepartmentLogger();
        departmentLogger.setLogin(loggerService.login());
        departmentLogger.setDateTime(loggerService.bishkekNow());
        departmentLogger.setBefore(before);
        departmentLogger.setAfter(after);
        departmentLogger.setStatus(status);
        return departmentLoggerRepo.save(departmentLogger);
    }
}
