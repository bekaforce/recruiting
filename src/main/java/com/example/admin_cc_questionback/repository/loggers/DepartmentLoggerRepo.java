package com.example.admin_cc_questionback.repository.loggers;

import com.example.admin_cc_questionback.entities.loggers.DepartmentLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentLoggerRepo extends JpaRepository<DepartmentLogger, Long> {

}
