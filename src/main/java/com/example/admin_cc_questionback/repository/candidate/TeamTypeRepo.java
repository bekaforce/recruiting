package com.example.admin_cc_questionback.repository.candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamType extends JpaRepository<TeamType, Long> {
    
}
