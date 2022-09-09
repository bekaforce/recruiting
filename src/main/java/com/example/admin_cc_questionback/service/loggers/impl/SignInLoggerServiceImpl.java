package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.loggers.QuestionLogger;
import com.example.admin_cc_questionback.entities.loggers.SignInLogger;
import com.example.admin_cc_questionback.repository.loggers.SignInLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.SignInLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignInLoggerServiceImpl implements SignInLoggerService {
    private final SignInLoggerRepo signInLoggerRepo;

    public SignInLoggerServiceImpl(SignInLoggerRepo signInLoggerRepo) {
        this.signInLoggerRepo = signInLoggerRepo;
    }

    @Override
    public void save(SignInLogger signInLogger) {
        signInLoggerRepo.save(signInLogger);
    }

    @Override
    public List<SignInLogger> all() {
        return signInLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(SignInLogger::getId).reversed()).collect(Collectors.toList());
    }
}
