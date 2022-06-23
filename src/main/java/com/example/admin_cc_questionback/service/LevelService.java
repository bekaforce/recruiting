package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;

public interface LevelService {
    Level save(LevelDto levelDto);
    Level update(Long id, String name);
    boolean delete(Long id);
}
