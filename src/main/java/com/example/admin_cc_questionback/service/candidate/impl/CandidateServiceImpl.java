package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.dtos.GetCandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.dtos.InvitationDto;
import com.example.admin_cc_questionback.entities.interview.Essay;
import com.example.admin_cc_questionback.entities.interview.VideoResult;
import com.example.admin_cc_questionback.repository.candidate.CandidateRepo;
import com.example.admin_cc_questionback.service.candidate.CandidateService;
import com.example.admin_cc_questionback.service.impl.ParticipantServiceImpl;
import com.example.admin_cc_questionback.service.impl.Status;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;
    private final ParticipantServiceImpl participantService;
    private final EmailSenderServiceImpl emailSenderService;
    private final EncoderServiceImpl encoderService;

    public CandidateServiceImpl(CandidateRepo candidateRepo, ParticipantServiceImpl participantService, EmailSenderServiceImpl emailSenderService, EncoderServiceImpl encoderService) {
        this.candidateRepo = candidateRepo;
        this.participantService = participantService;
        this.emailSenderService = emailSenderService;
        this.encoderService = encoderService;
    }

    @Override
    public Candidate candidateById(Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate candidate = candidateRepo.findCandidateById(id);
        if (candidate != null){
            List<VideoResult> videoResults = sortVideoResults(candidate.getVideoResults());
            List<Essay> essays = sortEssays(candidate.getEssays());
            candidate.setVideoResults(videoResults);
            candidate.setEssays(essays);
            candidate = decodePersonalInfo(candidate);
        }
        return candidate;
    }

    @Override
    public List<CandidateDto> all(Long candidateType_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<GetCandidateDto> result = candidateRepo.all(candidateType_id);
        if (result != null){
            return decodeCandidates(result);
        }
        return null;
    }

    @Override
    public List<CandidateDto> allFailed(Long candidateType_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<GetCandidateDto> result = candidateRepo.allFailed(candidateType_id);
        if (result != null){
            return decodeCandidates(result);
        }
        return null;
    }

    @Override
    public List<CandidateDto> allOnInterview(Long candidateType_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<GetCandidateDto> result = candidateRepo.allOnInterview(candidateType_id);
        if (result != null){
            return decodeCandidates(result);
        }
        return null;
    }

    @Override
    public String setComment(Long id, String comment) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate candidate = candidateById(id);
        if (candidate != null){
            candidate.setComment(comment);
            candidateRepo.save(candidate);
            return comment;
        }
        return null;
    }

    @Override
    public Candidate setStatus(InvitationDto invitationDto, Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate candidate = candidateById(id);
        if (candidate != null){
            if (invitationDto.getStatus().equals(Status.INVITE)){
                candidate.setStatus(Status.INVITED);
                candidate.setInvitationDate(invitationDto.getInvitationDate());
                candidate.setGender(invitationDto.getGender());
                emailSenderService.sendCongratulationMessage(candidate);
            }
            else {
                candidate.setStatus(Status.REJECTED);
            }
            checkCandidateAndSaveParticipant(candidate);
            candidate = encodePersonalInfo(candidate);
            return candidateRepo.save(candidate);
        }
        return null;
    }

    @Override
    public List<VideoResult> sortVideoResults(List<VideoResult> videoResults) {
        if (!videoResults.isEmpty()){
            return videoResults.stream().sorted(Comparator.comparingLong(VideoResult::getId)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Essay> sortEssays(List<Essay> essays) {
        if (!essays.isEmpty()){
            return essays.stream().sorted(Comparator.comparingLong(Essay::getId)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<String> inviteOrReject() {
        List<String> select = new ArrayList<>();
        select.add(Status.INVITE);
        select.add(Status.REJECT);
        return select;
    }

    @Override
    public void checkCandidateAndSaveParticipant(Candidate candidate) {
        if (candidate.getCandidateType().getTeamType().isToEducate()){
            LocalDate birthday = LocalDate.parse(candidate.getBirthday());
            participantService.save(candidate.getName(), candidate.getSurname(), birthday, candidate.getEmail(), candidate.getAddress(), candidate.getCandidateType().getCandidateType(), candidate.getStatus(), candidate.getPhoneNumber(), candidate.getCandidateType().getCity(), candidate.getInvitationDate(), candidate.getGender());
        }
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
    public List<CandidateDto> decodeCandidates(List<GetCandidateDto> list) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<CandidateDto> candidates = new ArrayList<>();
        for (GetCandidateDto getCandidateDto : list){
            CandidateDto candidateDto = new CandidateDto();
            candidateDto.setId(getCandidateDto.getId());
            candidateDto.setName(encoderService.decrypt(getCandidateDto.getName()));
            candidateDto.setStatus(getCandidateDto.getStatus());
            candidateDto.setRegistration_Date(getCandidateDto.getRegistration_Date());
            candidates.add(candidateDto);
        }
        return candidates;
    }
}
