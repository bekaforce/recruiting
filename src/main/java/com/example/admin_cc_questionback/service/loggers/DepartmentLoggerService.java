package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.DepartmentLogger;

import java.util.List;

public interface DepartmentLoggerService {
    List<DepartmentLogger> all();
    DepartmentLogger logger(String before, String after, String status, String entity);
}
