package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.dtos.DepartmentDto;
import com.example.admin_cc_questionback.repository.candidate.DepartmentRepo;
import com.example.admin_cc_questionback.service.candidate.DepartmentService;
import com.example.admin_cc_questionback.service.loggers.impl.DepartmentLoggerServiceImpl;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerService;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final DepartmentLoggerServiceImpl departmentLoggerService;
    private final LoggerService loggerService;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo, DepartmentLoggerServiceImpl departmentLoggerService, LoggerService loggerService) {
        this.departmentRepo = departmentRepo;
        this.departmentLoggerService = departmentLoggerService;
        this.loggerService = loggerService;
    }

    @Override
    public Department save(DepartmentDto departmentDto) {
        Department department = departmentRepo.save(new Department(departmentDto.getName()));
        saveCreatedDepartmentToLogs(department.getName());
        return department;
    }

    @Override
    public List<Department> all() {
        return departmentRepo.findAll();
    }

    @Override
    public boolean delete(Long id) {
        Department department = departmentById(id);
        if (department != null){
            saveDeletedDepartmentToLogs(department.getName());
            departmentRepo.delete(department);
            return true;
        }
        return false;
    }

    @Override
    public Department update(DepartmentDto departmentDto, Long id) {
        Department department = departmentById(id);
        if (department != null){
            String before = department.getName();
            department.setName(departmentDto.getName());
            departmentRepo.save(department);
            saveUpdatedDepartmentToLogs(before, departmentDto.getName());
            return department;
        }
        return null;
    }

    @Override
    public Department departmentById(Long id) {
        return departmentRepo.findDepartmentById(id);
    }

    @Override
    public void saveUpdatedDepartmentToLogs(String before, String after) {
        if (!loggerService.beforeAndAfter(before, after)){
            departmentLoggerService.save(before, after, LoggerStatus.UPDATED);
        }
    }

    @Override
    public void saveCreatedDepartmentToLogs(String name) {
        departmentLoggerService.save(loggerService.empty, name, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedDepartmentToLogs(String name) {
        departmentLoggerService.save(name, loggerService.empty, LoggerStatus.DELETED);
    }
}
