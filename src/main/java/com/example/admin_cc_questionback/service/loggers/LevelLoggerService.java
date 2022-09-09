package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.LevelLogger;

import java.util.List;

public interface LevelLoggerService {
    LevelLogger save(String name, String knowledge, String status);
    LevelLogger saveUpdate(String nameBefore, String nameAfter, String knowledge);
    List<LevelLogger> all();
}
