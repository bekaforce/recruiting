package com.example.admin_cc_questionback.service.impl.loggers;

import com.example.admin_cc_questionback.entities.loggers.DepartmentLogger;
import com.example.admin_cc_questionback.repository.loggers.DepartmentLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.DepartmentLoggerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class DepartmentLoggerServiceImpl implements DepartmentLoggerService {
    private final DepartmentLoggerRepo departmentLoggerRepo;

    public DepartmentLoggerServiceImpl(DepartmentLoggerRepo departmentLoggerRepo) {
        this.departmentLoggerRepo = departmentLoggerRepo;
    }

    @Override
    public List<DepartmentLogger> all() {
        return departmentLoggerRepo.findAll();
    }

    @Override
    public DepartmentLogger logger(String before, String after, String status, String entity) {
        DepartmentLogger departmentLogger = new DepartmentLogger();
        departmentLogger.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        departmentLogger.setLocalDateTime(LocalDateTime.now(ZoneId.of(LoggerService.bishkek)));
        departmentLogger.setBefore(before);
        departmentLogger.setAfter(after);
        departmentLogger.setStatus(status);
        return departmentLoggerRepo.save(departmentLogger);
    }
}
