package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.TeamTypeDto;
import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.entities.loggers.TeamTypeLogger;
import com.example.admin_cc_questionback.repository.loggers.TeamTypeLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.TeamTypeLoggerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamTypeLoggerServiceImpl implements TeamTypeLoggerService {
    private final TeamTypeLoggerRepo teamTypeLoggerRepo;
    private final LoggerService loggerService;

    public TeamTypeLoggerServiceImpl(TeamTypeLoggerRepo teamTypeLoggerRepo, LoggerService loggerService) {
        this.teamTypeLoggerRepo = teamTypeLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public List<TeamTypeLogger> all() {
        return teamTypeLoggerRepo.findAll();
    }

    @Override
    public void save(TeamType teamType, String status) {
        TeamTypeLogger teamTypeLogger = new TeamTypeLogger();
        teamTypeLogger.setName(teamType.getName());
        teamTypeLogger.setToEducate(String.valueOf(teamType.isToEducate()));
        teamTypeLogger.setStatus(status);
        teamTypeLogger.setLogin(loggerService.login());
        teamTypeLogger.setDateTime(loggerService.bishkekNow());
        if (teamType.getDepartment() != null){
            teamTypeLogger.setDepartment(teamType.getDepartment().getName());
        }
        teamTypeLoggerRepo.save(teamTypeLogger);
    }

    @Override
    public void saveUpdate(TeamTypeDto teamTypeDto, TeamType teamType, String departmentNameAfter) {
        TeamTypeLogger teamTypeLogger = new TeamTypeLogger();
        teamTypeLogger.setName(loggerService.setParam(teamType.getName(), teamTypeDto.getName()));
        teamTypeLogger.setToEducate(loggerService.setParam(String.valueOf(teamType.isToEducate()), String.valueOf(teamTypeDto.isToEducate())));
        teamTypeLogger.setStatus(LoggerStatus.UPDATED);
        teamTypeLogger.setDateTime(loggerService.bishkekNow());
        teamTypeLogger.setLogin(loggerService.login());
        teamTypeLogger.setDepartment(loggerService.setParam(teamType.getDepartment().getName(), departmentNameAfter));
        teamTypeLoggerRepo.save(teamTypeLogger);
    }
}
