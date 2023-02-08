package com.example.admin_cc_questionback.repository.candidate;


import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateTypeRepo extends JpaRepository<CandidateType, Long> {
    CandidateType findCandidateTypeById(Long candidateTypeId);

    @Query(value = "select * from vcv.candidate_type c order by c.id", nativeQuery = true)
    List<CandidateType> findAllOrderById();
}