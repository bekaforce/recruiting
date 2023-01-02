package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.SignInLogger;

import java.util.List;

public interface SignInLoggerService {
    void save(String username);
    List<SignInLogger> all();
}
