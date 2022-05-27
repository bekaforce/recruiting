package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.dtos.EducationDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.Language;
import com.beeline.cc_question.entities.Program;
import com.beeline.cc_question.repos.CandidateRepo;
import com.beeline.cc_question.services.CandidateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;

    public CandidateServiceImpl(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    @Override
    public Candidate saveCandidate(CandidateDto candidateDto) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        Candidate candidate = new Candidate();
        candidate.setVacancy(candidateDto.getVacancy());
        candidate.setName(candidateDto.getName());
        candidate.setPhoneNumber(candidateDto.getPhoneNumber());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setCitizenship(candidateDto.getCitizenship());
        candidate.setBirthday(candidateDto.getBirthday());
        candidate.setAddress(candidateDto.getAddress());
        candidate.setMaritalStatus(candidateDto.getMaritalStatus());
        candidate.setEducation(candidateDto.getEducation());
        List<Language> languages = candidateDto.getLanguages();
        languages.removeIf(language -> !language.isActive() || language.getLevel().equals(""));
        candidate.setLanguages(languages);
        candidate.setPrograms(candidateDto.getPrograms());
        candidate.setSchedule(candidateDto.getSchedule());
        candidate.setRegistrationDate(now);
        return candidateRepo.save(candidate);
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidateRepo.getCandidateById(id);
    }


}
