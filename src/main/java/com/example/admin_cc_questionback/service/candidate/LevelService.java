package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;

public interface LevelService {
    Level save(LevelDto levelDto);
    Level update(Long id, String name);
    boolean delete(Long id);
}
