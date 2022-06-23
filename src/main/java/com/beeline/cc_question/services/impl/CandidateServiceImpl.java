package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.CandidateType;
import com.beeline.cc_question.repos.CandidateRepo;
import com.beeline.cc_question.services.CandidateService;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;
    private final CandidateTypeServiceImpl candidateTypeService;

    public CandidateServiceImpl(CandidateRepo candidateRepo, CandidateTypeServiceImpl candidateTypeService) {
        this.candidateRepo = candidateRepo;
        this.candidateTypeService = candidateTypeService;
    }

    @Override
    public Candidate save(CandidateDto candidateDto) {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
            Candidate candidate = new Candidate();
            candidate.setName(candidateDto.getName());
            candidate.setPhoneNumber(candidateDto.getPhoneNumber());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setCitizenship(candidateDto.getCitizenship());
            candidate.setBirthday(candidateDto.getBirthday());
            candidate.setAddress(candidateDto.getAddress());
            candidate.setEducation(candidateDto.getEducation());
            candidate.setExperience(candidateDto.getExperience());
            candidate.setSchedule(candidateDto.getSchedule());
            candidate.setRegistrationDate(now);
            candidate.setComment(null);
            CandidateType candidateType = candidateTypeService.candidateTypeById(candidateDto.getCandidateType_id());
            candidate.setCandidateType(candidateType);
            candidate.setStatus("На рассмотрении");
            candidate.setQuestionnaire(candidateDto.getQuestionnaireList());
            candidate.setInvitationDate(null);
            candidate.setStage("testing");
            return candidateRepo.save(candidate);
    }

    @Override
    public Candidate candidateById(Long id) {
        return candidateRepo.findCandidateById(id);
    }

    @Override
    public List<Candidate> getAll() {
        return candidateRepo.findAll();
    }

    @Override
    public Candidate setStage(Candidate candidate, String stage) {
        candidate.setStage(stage);
        return candidateRepo.save(candidate);
    }

    @Override
    public String getStage(Long candidate_id) {
        Candidate candidate = candidateById(candidate_id);
        if (candidate != null){
            return candidate.getStage();
        }
        else {
            return "Срок истек";
        }
    }

    @Override
    public Candidate candidateByEmail(String email) {
        return candidateRepo.findCandidateByEmail(email);
    }

    @Override
    public boolean expiration(LocalDateTime registration_date) {
        long daysToExpire = 1L;
        LocalDateTime expirationDate = registration_date.plusDays(daysToExpire);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        return now.compareTo(expirationDate) > 0;
    }


}
