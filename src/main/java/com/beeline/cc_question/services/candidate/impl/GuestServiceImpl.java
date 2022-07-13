package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;
import com.beeline.cc_question.entities.user.User;
import com.beeline.cc_question.services.candidate.GuestService;
import com.beeline.cc_question.services.user.impl.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class GuestServiceImpl implements GuestService {
    private final MessageServiceImpl messageService;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CandidateServiceImpl candidateService;

    public GuestServiceImpl(MessageServiceImpl messageService, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder, CandidateServiceImpl candidateService) {
        this.messageService = messageService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.candidateService = candidateService;
    }

    //String name, String phoneNumber, String email
    @Override
    public Candidate add(CandidateDto candidateDto) {
        String password = String.valueOf(generateDigits());
            if (sendMessage(candidateDto.getName(), candidateDto.getEmail(), password)){
                String encoded = passwordEncoder.encode(password);
                userService.register(candidateDto.getEmail(), candidateDto.getPhoneNumber(), encoded);
                return candidateService.save(candidateDto);
            }
            else {
                return null;
            }
    }

    @Override
    public boolean sendMessage(String name, String email, String password){
        return messageService.sendEmail(name, email, password);
    }

    @Override
    public int generateDigits() {
        int min = 1000;
        int max = 9999;
        return (int) (min + Math.random() * (max + min));
    }

    @Override
    public Long setPassword(String password, Long id) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (password.matches(pattern)){
            User user = userService.findById(id);
            user.setPassword(password);
            return id;
        }
        else {
            return null;
        }
    }

    @Override
    public SuccessDto success(Long candidate_id) {
        Candidate candidate = candidateService.candidateById(candidate_id);
        if (candidate != null){
            return messageService.success(candidate.getName(), candidate.getCandidateType().getCandidateType());
        }
        return null;
    }
}
