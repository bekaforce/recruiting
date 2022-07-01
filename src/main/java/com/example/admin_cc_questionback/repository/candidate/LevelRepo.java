package com.example.admin_cc_questionback.repository.candidate;

import com.example.admin_cc_questionback.entities.candidate.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo extends JpaRepository<Level, Long> {
    Level findLevelById(Long id);
}
