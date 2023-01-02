package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.interview.VideoResult;

import java.time.LocalDate;
import java.util.List;

public interface CandidateService {
    Candidate candidateById(Long id);
    List<CandidateDto> all(Long candidateType_id);
    List<CandidateDto> allFailed(Long candidateType_id);
    List<CandidateDto> allOnVideo(Long candidateType_id);
    String setComment(Long id, String comment);
    String setStatus(String status, Long id);
    LocalDate setInvitationDate(LocalDate invitationDate, Long id);
    boolean setGender(Long id, String gender);
    List<VideoResult> sortVideoResults(List<VideoResult> videoResults);
}
