package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, Long> {

}
