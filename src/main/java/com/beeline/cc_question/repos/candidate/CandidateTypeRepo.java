package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.CandidateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateTypeRepo extends JpaRepository<CandidateType, Long> {
    CandidateType findCandidateTypeById(Long id);
    @Query(value = "select * from vcv.candidate_type c order by c.id", nativeQuery = true)
    List<CandidateType> all();

    @Query(value = "select * from vcv.candidate_type ct where ct.active = true and ct.internal = false", nativeQuery = true)
    List<CandidateType> allActiveAndExternal();

    @Query(value = "select * from vcv.candidate_type ct where ct.active = true and ct.internal = true", nativeQuery = true)
    List<CandidateType> allActiveAndInternal();
}
