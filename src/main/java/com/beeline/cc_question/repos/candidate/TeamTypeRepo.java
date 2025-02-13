package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamTypeRepo extends JpaRepository<TeamType, Long> {
}
