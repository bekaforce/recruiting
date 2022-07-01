package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Experience;
import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.candidate.CandidateType;
import com.beeline.cc_question.repos.candidate.CandidateRepo;
import com.beeline.cc_question.services.candidate.CandidateService;
import com.beeline.cc_question.services.impl.HooliganServiceImpl;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;
    private final CandidateTypeServiceImpl candidateTypeService;
    private final HooliganServiceImpl hooliganService;

    public CandidateServiceImpl(CandidateRepo candidateRepo, CandidateTypeServiceImpl candidateTypeService, HooliganServiceImpl hooliganService) {
        this.candidateRepo = candidateRepo;
        this.candidateTypeService = candidateTypeService;
        this.hooliganService = hooliganService;
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
            candidate.setExperience(setExperience(candidateDto.getExperience()));
            candidate.setSchedule(candidateDto.getSchedule());
            candidate.setRegistrationDate(now);
            candidate.setComment(null);
            CandidateType candidateType = candidateTypeService.candidateTypeById(candidateDto.getCandidateType_id());
            candidate.setCandidateType(candidateType);
            candidate.setStatus(setStatus(candidateDto.getName(), candidateDto.getBirthday()));
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
    public String setStatus(String name, LocalDate birthday) {
       if (hooliganService.checkCandidate(name, birthday)){
           return "В черном списке";
       }
       else {
           return "На рассмотрении";
       }
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

    @Override
    public Experience setExperience(Experience experience) {
        if (experience.getName().isEmpty()){
            experience.setName("Нет опыта");
            experience.setEndDate(null);
            experience.setStartDate(null);
            experience.setPosition("");
        }
        return experience;
    }


}
