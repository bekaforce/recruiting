package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.Candidate;
import com.example.admin_cc_questionback.repository.CandidateRepo;
import com.example.admin_cc_questionback.service.CandidateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;
    private final ParticipantServiceImpl participantService;

    public CandidateServiceImpl(CandidateRepo candidateRepo, ParticipantServiceImpl participantService) {
        this.candidateRepo = candidateRepo;
        this.participantService = participantService;
    }

    @Override
    public Candidate candidateById(Long id) {
        return candidateRepo.findCandidateById(id);
    }

    @Override
    public List<CandidateDto> all(Long candidateType_id) {
        return candidateRepo.all(candidateType_id);
    }

    @Override
    public String comment(Long id, String comment) {
        Candidate candidate = candidateById(id);
        if (candidate != null){
            candidate.setComment(comment);
            candidateRepo.save(candidate);
            return comment;
        }
        return null;
    }

    @Override
    public String setStatus(String status, Long id) {
        Candidate candidate = candidateById(id);
        if (candidate != null){
            candidate.setStatus(status);
            candidateRepo.save(candidate);
            participantService.save(candidate.getName(), candidate.getBirthday(), candidate.getEmail(), candidate.getAddress(), candidate.getCandidateType().getCandidateType(), status, candidate.getPhoneNumber());
            return status;
        }
        return null;
    }

    @Override
    public LocalDate setInvitationDate(LocalDate invitationDate, Long id) {
        Candidate candidate = candidateById(id);
        if (candidate != null){
            candidate.setInvitationDate(invitationDate);
            candidateRepo.save(candidate);
            return invitationDate;
        }
        return null;
    }
}
