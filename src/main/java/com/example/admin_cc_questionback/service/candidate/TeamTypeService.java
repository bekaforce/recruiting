package com.example.admin_cc_questionback.service.candidate;


import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.TeamTypeDto;

import java.util.List;

public interface TeamTypeService {
    TeamType save(TeamTypeDto teamTypeDto);
    TeamType update(TeamTypeDto teamTypeDto, Long id);
    boolean delete(Long id);
    List<TeamType> all();
    TeamType teamTypeById(Long id);
    void saveCreatedTeamTypeToLogs(TeamType teamType);
    void saveDeletedTeamTypeToLogs(TeamType teamType);
    void saveUpdatedTeamTypeToLogs(TeamTypeDto teamTypeDto, TeamType teamType, String departmentNameAfter);
    TeamType setTeamType(TeamType teamType, TeamTypeDto teamTypeDto, Department department);
    String getDepartmentName(Department department);
}
