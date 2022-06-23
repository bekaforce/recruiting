package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.Candidate;

import java.time.LocalDate;
import java.util.List;

public interface CandidateService {
    Candidate candidateById(Long id);
    List<CandidateDto> all(Long candidateType_id);
    String comment(Long id, String comment);
    String setStatus(String status, Long id);
    LocalDate setInvitationDate(LocalDate invitationDate, Long id);
}
