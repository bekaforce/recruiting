package com.example.admin_cc_questionback.repository.candidate;

import com.example.admin_cc_questionback.entities.dtos.GetCandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate findCandidateById(Long id);

    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage = 'completed' and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> all(@Param("candidate_type_id") Long candidate_type_id);

    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage = 'failed' and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> allFailed(@Param("candidate_type_id") Long candidate_type_id);

    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage in ('choice','video','essay') and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> allOnInterview(@Param("candidate_type_id") Long candidate_type_id);
}
