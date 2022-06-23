package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Department;
import com.example.admin_cc_questionback.entities.dtos.DepartmentDto;
import com.example.admin_cc_questionback.repository.DepartmentRepo;
import com.example.admin_cc_questionback.service.DepartmentService;
import com.example.admin_cc_questionback.service.impl.loggers.DepartmentLoggerServiceImpl;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerService;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerStatus;
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
        departmentLoggerService.logger(loggerService.empty, department.getName(), LoggerStatus.CREATED, department.getName());
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
            departmentLoggerService.logger(department.getName(), loggerService.empty, LoggerStatus.DELETED, department.getName());
            departmentRepo.delete(departmentById(id));
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
            if (!loggerService.beforeAndAfter(before, departmentDto.getName())){
                departmentLoggerService.logger(before, department.getName(), LoggerStatus.UPDATED, department.getName());
            }
            return department;
        }
        return null;
    }

    @Override
    public Department departmentById(Long id) {
        return departmentRepo.findDepartmentById(id);
    }
}
