package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.TeamTypeDto;
import com.example.admin_cc_questionback.entities.loggers.TeamTypeLogger;

import java.util.List;

public interface TeamTypeLoggerService {
    List<TeamTypeLogger> all();
    void save(TeamType teamType, String status);
    void saveUpdate(TeamTypeDto teamTypeDto, TeamType teamType, String departmentNameAfter);
}
