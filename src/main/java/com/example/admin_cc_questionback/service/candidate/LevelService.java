package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;
import com.example.admin_cc_questionback.entities.dtos.LevelUpdateDto;

public interface LevelService {
    Level save(LevelDto levelDto);
    Level update(LevelUpdateDto levelUpdateDto);
    boolean delete(Long id);
    void saveCreatedLevelToLogs(String level, String knowledge);
    void saveDeletedLevelToLogs(String level, String knowledge);
    void saveUpdatedLevelToLogs(String before, String level, String knowledge);
}
