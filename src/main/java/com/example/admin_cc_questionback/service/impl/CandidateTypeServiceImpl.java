package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.CandidateType;
import com.example.admin_cc_questionback.entities.Department;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;
import com.example.admin_cc_questionback.repository.CandidateTypeRepo;
import com.example.admin_cc_questionback.service.CandidateTypeService;
import com.example.admin_cc_questionback.service.impl.loggers.CandidateTypeLoggerServiceImpl;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateTypeServiceImpl implements CandidateTypeService {
    private final CandidateTypeRepo candidateTypeRepo;
    private final DepartmentServiceImpl departmentService;
    private final CandidateTypeLoggerServiceImpl candidateTypeLoggerService;

    public CandidateTypeServiceImpl(CandidateTypeRepo candidateTypeRepo, DepartmentServiceImpl departmentService, CandidateTypeLoggerServiceImpl candidateTypeLoggerService) {
        this.candidateTypeRepo = candidateTypeRepo;
        this.departmentService = departmentService;
        this.candidateTypeLoggerService = candidateTypeLoggerService;
    }

    @Override
    public CandidateType save(CandidateTypeDto candidateTypeDto) {
        CandidateType candidateType = new CandidateType();
        Department department = departmentService.departmentById(candidateTypeDto.getDepartmentId());
        if (department != null){
            candidateType.setDepartment(department);
            candidateType.setCandidateType(candidateTypeDto.getCandidateType());
            candidateType.setInternal(candidateTypeDto.isInternal());
            candidateType.setActive(candidateTypeDto.isActive());
            candidateTypeRepo.save(candidateType);
            candidateTypeLoggerService.save(candidateType.getCandidateType(), candidateType.isInternal(), candidateType.isActive(), department.getName(), LoggerStatus.CREATED);
            return candidateType;
        }
        return null;
    }

    @Override
    public List<CandidateType> all() {
        return candidateTypeRepo.findAllOrderById();
    }

    @Override
    public boolean delete(Long id) {
        CandidateType candidateType = candidateTypeById(id);
        if (candidateType != null){
            candidateTypeRepo.deleteById(id);
            candidateTypeLoggerService.save(candidateType.getCandidateType(), candidateType.isInternal(), candidateType.isActive(), candidateType.getDepartment().getName(), LoggerStatus.DELETED);
            return true;
        }
        return false;
    }

    @Override
    public CandidateType update(CandidateTypeDto candidateTypeDto, Long id) {
        CandidateType candidateType = candidateTypeById(id);
        Department before = departmentService.departmentById(candidateType.getDepartment().getId());
        Department after  = departmentService.departmentById(candidateTypeDto.getDepartmentId());
        if (after != null){
            candidateTypeLoggerService.update(candidateTypeDto, candidateType, before, after);
            candidateType.setDepartment(after);
            candidateType.setCandidateType(candidateTypeDto.getCandidateType());
            candidateType.setInternal(candidateTypeDto.isInternal());
            candidateType.setActive(candidateTypeDto.isActive());
            candidateTypeRepo.save(candidateType);
            return candidateType;
        }
        return null;
    }

    @Override
    public CandidateType candidateTypeById(Long id) {
        return candidateTypeRepo.findCandidateTypeById(id);
    }
}
