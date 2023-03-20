package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;
import com.beeline.cc_question.entities.user.User;
import com.beeline.cc_question.services.candidate.GuestService;
import com.beeline.cc_question.services.user.impl.UserServiceImpl;
import org.apache.commons.codec.DecoderException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;


@Service
public class GuestServiceImpl implements GuestService {
    private final EmailSenderServiceImpl emailSenderService;
    private final MessageServiceImpl messageService;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CandidateServiceImpl candidateService;

    public GuestServiceImpl(EmailSenderServiceImpl emailSenderService, MessageServiceImpl messageService, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder, CandidateServiceImpl candidateService) {
        this.emailSenderService = emailSenderService;
        this.messageService = messageService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.candidateService = candidateService;
    }

    //String name, String phoneNumber, String email
    @Override
    public Candidate add(CandidateDto candidateDto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String password = String.valueOf(generateDigits());
        Candidate candidate = candidateService.save(candidateDto);
        if (candidate != null) {
            String encoded = passwordEncoder.encode(password);
            userService.register(candidateDto.getEmail(), candidateDto.getPhoneNumber(), encoded);
            emailSenderService.sendAuthEmail(candidateDto.getName(), candidateDto.getEmail(), password);
            return candidate;
        } else {
            return null;
        }
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
    public SuccessDto success(Long candidate_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate candidate = candidateService.decodedCandidateById(candidate_id);
        if (candidate != null){
            emailSenderService.sendConfirmationEmail(candidate);
            return messageService.success(candidate.getName(), candidate.getCandidateType().getCandidateType());
        }
        return null;
    }
}
