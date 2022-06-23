package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.LevelLogger;

public interface LevelLoggerService {
    LevelLogger save(String name, String knowledge, String status);
    LevelLogger saveUpdate(String nameBefore, String nameAfter, String knowledge);
}
