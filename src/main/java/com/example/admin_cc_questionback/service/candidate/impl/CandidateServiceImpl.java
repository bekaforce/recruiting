package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.interview.VideoResult;
import com.example.admin_cc_questionback.repository.candidate.CandidateRepo;
import com.example.admin_cc_questionback.service.candidate.CandidateService;
import com.example.admin_cc_questionback.service.impl.ParticipantServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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
        Candidate candidate = candidateRepo.findCandidateById(id);
        if (candidate != null){
            List<VideoResult> videoResults = sortVideoResults(candidate.getVideoResults());
            candidate.setVideoResults(videoResults);
        }
        return candidate;
    }

    @Override
    public List<CandidateDto> all(Long candidateType_id) {
        return candidateRepo.all(candidateType_id);
    }

    @Override
    public String setComment(Long id, String comment) {
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

            participantService.save(candidate.getName(), candidate.getSurname(), candidate.getBirthday(), candidate.getEmail(), candidate.getAddress(), candidate.getCandidateType().getTeamType(), status, candidate.getPhoneNumber(), candidate.getCandidateType().getCity(), candidate.getInvitationDate(), candidate.getGender());
            candidateRepo.save(candidate);
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

    @Override
    public boolean setGender(Long id, String gender) {
        Candidate candidate = candidateById(id);
        if (candidate != null){
            candidate.setGender(gender);
            candidateRepo.save(candidate);
            return true;
        }
        return false;
    }

    @Override
    public List<VideoResult> sortVideoResults(List<VideoResult> videoResults) {
        if (!videoResults.isEmpty()){
            return videoResults.stream().sorted(Comparator.comparingLong(VideoResult::getId)).collect(Collectors.toList());
        }
        return null;
    }
}
