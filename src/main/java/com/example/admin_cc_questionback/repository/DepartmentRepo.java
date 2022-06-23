package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Department findDepartmentById(Long id);
}
