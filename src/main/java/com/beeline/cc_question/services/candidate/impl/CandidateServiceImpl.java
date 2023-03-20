package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Experience;
import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.candidate.CandidateType;
import com.beeline.cc_question.repos.candidate.CandidateRepo;
import com.beeline.cc_question.services.candidate.CandidateService;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;
    private final CandidateTypeServiceImpl candidateTypeService;
    private final HooliganServiceImpl hooliganService;
    private final EncoderServiceImpl encoderService;

    public CandidateServiceImpl(CandidateRepo candidateRepo, CandidateTypeServiceImpl candidateTypeService, HooliganServiceImpl hooliganService, EncoderServiceImpl encoderService) {
        this.candidateRepo = candidateRepo;
        this.candidateTypeService = candidateTypeService;
        this.hooliganService = hooliganService;
        this.encoderService = encoderService;
    }

    @Override
    public Candidate save(CandidateDto candidateDto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        CandidateType candidateType = candidateTypeService.candidateTypeById(candidateDto.getCandidateType_id());
        if (candidateType != null) {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
            Candidate candidate = new Candidate();
            candidate.setName(candidateDto.getName());
            candidate.setSurname(candidateDto.getSurname());
            candidate.setPhoneNumber(candidateDto.getPhoneNumber());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setBirthday(formatDateToString(candidateDto.getBirthday()));
            candidate.setAddress(candidateDto.getAddress());
            candidate.setEducation(candidateDto.getEducation());
            candidate.setExperience(setExperience(candidateDto.getExperience()));
            candidate.setSchedule(candidateDto.getSchedule());
            candidate.setRegistrationDate(now);
            candidate.setComment(null);
            candidate.setCandidateType(candidateType);
            candidate.setStatus(checkCandidate(candidateDto.getName(), candidate.getSurname(), candidateDto.getBirthday()));
            candidate.setQuestionnaire(candidateDto.getQuestionnaireList());
            candidate.setInvitationDate(null);
            candidate.setGender(null);
            candidate.setStage("testing");
            candidate.setArchive(false);
            candidate = encodePersonalInfo(candidate);
            return candidateRepo.save(candidate);
        }
        return null;
    }

    @Override
    public Candidate candidateById(Long id) {
        return candidateRepo.findCandidateById(id);
    }

    @Override
    public Candidate decodedCandidateById(Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate candidate = candidateRepo.findCandidateById(id);
        candidate = decodePersonalInfo(candidate);
        return candidate;
    }

    @Override
    public Candidate setStage(Candidate candidate, String stage) {
        candidate.setStage(stage);
        return candidateRepo.save(candidate);
    }

    @Override
    public String checkCandidate(String name, String surname, LocalDate birthday) {
       if (hooliganService.isCandidateHooligan(name, surname, birthday)){
           return "В черном списке";
       }
       else {
           return "На рассмотрении";
       }
    }

    @Override
    public String getStage(Candidate candidate) {
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
        long daysToExpire = 2L;
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

    @Override
    public Candidate encodePersonalInfo(Candidate candidate) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        candidate.setName(encoderService.encrypt(candidate.getName()));
        candidate.setSurname(encoderService.encrypt(candidate.getSurname()));
        candidate.setPhoneNumber(encoderService.encrypt(candidate.getPhoneNumber()));
        candidate.setAddress(encoderService.encrypt(candidate.getAddress()));
        candidate.setEmail(encoderService.encrypt(candidate.getEmail()));
        candidate.setBirthday(encoderService.encrypt(candidate.getBirthday()));
        return candidate;
    }

    @Override
    public Candidate decodePersonalInfo(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        candidate.setName(encoderService.decrypt(candidate.getName()));
        candidate.setSurname(encoderService.decrypt(candidate.getSurname()));
        candidate.setPhoneNumber(encoderService.decrypt(candidate.getPhoneNumber()));
        candidate.setAddress(encoderService.decrypt(candidate.getAddress()));
        candidate.setEmail(encoderService.decrypt(candidate.getEmail()));
        candidate.setBirthday(encoderService.decrypt(candidate.getBirthday()));
        return candidate;
    }

    @Override
    public String formatDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
