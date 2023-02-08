package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.TeamTypeDto;
import com.example.admin_cc_questionback.repository.candidate.TeamTypeRepo;
import com.example.admin_cc_questionback.service.candidate.TeamTypeService;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import com.example.admin_cc_questionback.service.loggers.impl.TeamTypeLoggerServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamTypeServiceImpl implements TeamTypeService {
    private final TeamTypeRepo teamTypeRepo;
    private final TeamTypeLoggerServiceImpl teamTypeLoggerService;
    private final DepartmentServiceImpl departmentService;

    public TeamTypeServiceImpl(TeamTypeRepo teamTypeRepo, TeamTypeLoggerServiceImpl teamTypeLoggerService, DepartmentServiceImpl departmentService) {
        this.teamTypeRepo = teamTypeRepo;
        this.teamTypeLoggerService = teamTypeLoggerService;
        this.departmentService = departmentService;
    }

    @Override
    public TeamType save(TeamTypeDto teamTypeDto) {
        Department department = departmentService.departmentById(teamTypeDto.getDepartmentId());
        if (department != null){
            TeamType teamType = new TeamType();
            teamType = setTeamType(teamType, teamTypeDto, department);
            saveCreatedTeamTypeToLogs(teamType);
            teamTypeRepo.save(teamType);
            return teamType;
        }
        return null;
    }

    @Override
    public TeamType update(TeamTypeDto teamTypeDto, Long id) {
        TeamType teamType = teamTypeById(id);
        if (teamType != null){
            Department department = departmentService.departmentById(teamTypeDto.getDepartmentId());
            saveUpdatedTeamTypeToLogs(teamTypeDto, teamType, department.getName());
            teamType = setTeamType(teamType, teamTypeDto, department);
            teamTypeRepo.save(teamType);
            return teamType;
        }
        return null;
    }

    @Override
    public String getDepartmentName(Department department) {
        if (department != null){
            return department.getName();
        }
        return "Not found";
    }

    @Override
    public boolean delete(Long id) {
        TeamType teamType = teamTypeById(id);
        if (teamType != null){
            saveDeletedTeamTypeToLogs(teamType);
            teamTypeRepo.delete(teamType);
            return true;
        }
        return false;
    }

    @Override
    public List<TeamType> all() {
        return teamTypeRepo.findAllOrderById();
    }

    @Override
    public TeamType teamTypeById(Long id) {
        return teamTypeRepo.findTeamTypeById(id);
    }

    @Override
    public void saveCreatedTeamTypeToLogs(TeamType teamType) {
        teamTypeLoggerService.save(teamType, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedTeamTypeToLogs(TeamType teamType) {
        teamTypeLoggerService.save(teamType, LoggerStatus.DELETED);
    }

    @Override
    public void saveUpdatedTeamTypeToLogs(TeamTypeDto teamTypeDto, TeamType teamType, String departmentNameAfter) {
        teamTypeLoggerService.saveUpdate(teamTypeDto, teamType, departmentNameAfter);
    }

    @Override
    public TeamType setTeamType(TeamType teamType, TeamTypeDto teamTypeDto, Department department) {
        teamType.setName(teamTypeDto.getName());
        teamType.setToEducate(teamTypeDto.isToEducate());
        teamType.setDepartment(department);
        return teamType;
    }

}
