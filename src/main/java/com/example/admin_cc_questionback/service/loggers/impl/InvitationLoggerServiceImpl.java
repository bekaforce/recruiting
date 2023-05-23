package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.loggers.InvitationLogger;
import com.example.admin_cc_questionback.repository.loggers.InvitationLoggerRepo;
import com.example.admin_cc_questionback.service.candidate.impl.EncoderServiceImpl;
import com.example.admin_cc_questionback.service.loggers.InvitationLoggerService;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationLoggerServiceImpl implements InvitationLoggerService {
    private final InvitationLoggerRepo invitationLoggerRepo;
    private final LoggerService loggerService;
    private final EncoderServiceImpl encoderService;
    public InvitationLoggerServiceImpl(InvitationLoggerRepo invitationLoggerRepo, LoggerService loggerService, EncoderServiceImpl encoderService) {
        this.invitationLoggerRepo = invitationLoggerRepo;
        this.loggerService = loggerService;
        this.encoderService = encoderService;
    }

    @Override
    public InvitationLogger save(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        InvitationLogger invitationLogger = new InvitationLogger();
        invitationLogger.setId(candidate.getId());
        invitationLogger.setLogin(loggerService.login());
        invitationLogger.setCandidateType(candidate.getCandidateType().getCandidateType());
        invitationLogger.setCandidate(candidate.getName());
        invitationLogger.setStatus(candidate.getStatus());
        invitationLogger.setLocation(candidate.getInvitationLocation());
        invitationLogger.setDateTime(loggerService.bishkekNow());
        return invitationLoggerRepo.save(invitationLogger);
    }
    @Override
    public List<InvitationLogger> getAll(){
        return invitationLoggerRepo
                .findAll()
                .stream()
                .peek(invitationLogger -> {
                    try {
                        invitationLogger.setCandidate(encoderService.decrypt(invitationLogger.getCandidate()));
                    } catch (InvalidKeyException | DecoderException | IllegalBlockSizeException | BadPaddingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted(Comparator.comparingLong(InvitationLogger::getId)
                        .reversed())
                .collect(Collectors.toList());
    }
}
