package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    Department save(DepartmentDto departmentDto);
    List<Department> all();
    boolean delete(Long id);
    Department update(DepartmentDto departmentDto, Long id);
    Department departmentById(Long id);
}
