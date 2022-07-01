package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate findCandidateById(Long id);
    @Query(value = "select * from vcv.candidate c " +
            "where c.email = :email " +
            "ORDER BY registration_date DESC " +
            "FETCH FIRST 1 ROWS WITH TIES", nativeQuery = true)
    Candidate findCandidateByEmail(@Param(value = "email") String email);
}
