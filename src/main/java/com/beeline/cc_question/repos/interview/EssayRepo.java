package com.beeline.cc_question.repos.interview;

import com.beeline.cc_question.entities.interview.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepo extends JpaRepository<Essay, Long> {
    @Query(value = "select max(e.position) from vcv.essay e where e.candidate_id = :candidate_id", nativeQuery = true)
    Long position(@Param(value = "candidate_id") Long candidate_id);
}
