package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeUpdateDto;
import com.example.admin_cc_questionback.repository.candidate.CandidateTypeRepo;
import com.example.admin_cc_questionback.service.candidate.CandidateTypeService;
import com.example.admin_cc_questionback.service.loggers.impl.CandidateTypeLoggerServiceImpl;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateTypeServiceImpl implements CandidateTypeService {
    private final CandidateTypeRepo candidateTypeRepo;
    private final TeamTypeServiceImpl teamTypeService;
    private final CandidateTypeLoggerServiceImpl candidateTypeLoggerService;

    public CandidateTypeServiceImpl(CandidateTypeRepo candidateTypeRepo, TeamTypeServiceImpl teamTypeService, CandidateTypeLoggerServiceImpl candidateTypeLoggerService) {
        this.candidateTypeRepo = candidateTypeRepo;
        this.teamTypeService = teamTypeService;
        this.candidateTypeLoggerService = candidateTypeLoggerService;
    }


    @Override
    public CandidateType save(CandidateTypeDto candidateTypeDto) {
        CandidateType candidateType = new CandidateType();
        TeamType teamType = teamTypeService.teamTypeById(candidateTypeDto.getTeamTypeId());
        if (teamType != null){
            candidateType.setTeamType(teamType);
            candidateType.setCandidateType(candidateTypeDto.getCandidateType());
            candidateType.setInternal(candidateTypeDto.isInternal());
            candidateType.setActive(candidateTypeDto.isActive());
            candidateType.setCity(candidateTypeDto.getCity());
            candidateType.setSchedule(candidateTypeDto.isSchedule());
            candidateTypeRepo.save(candidateType);
            candidateTypeLoggerService.save(candidateType.getCandidateType(), candidateType.isInternal(), candidateType.isActive(), teamType.getName(), LoggerStatus.CREATED, candidateType.isSchedule());
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
            candidateTypeLoggerService.save(candidateType.getCandidateType(), candidateType.isInternal(), candidateType.isActive(), candidateType.getTeamType().getName(), LoggerStatus.DELETED, candidateType.isSchedule());
            return true;
        }
        return false;
    }

    @Override
    public CandidateType update(CandidateTypeUpdateDto candidateTypeDto, Long id) {
        CandidateType candidateType = candidateTypeById(id);
        if (candidateType != null){
            TeamType teamType = candidateType.getTeamType();
            candidateTypeLoggerService.update(candidateTypeDto, candidateType, teamType);
            candidateType.setCandidateType(candidateTypeDto.getCandidateType());
            candidateType.setInternal(candidateTypeDto.isInternal());
            candidateType.setActive(candidateTypeDto.isActive());
            candidateType.setCity(candidateTypeDto.getCity());
            candidateType.setSchedule(candidateTypeDto.isSchedule());
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
