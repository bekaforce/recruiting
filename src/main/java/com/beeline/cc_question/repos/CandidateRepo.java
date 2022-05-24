package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate getCandidateById(Long id);
}
