package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate findCandidateById(Long id);

    @Query(value = "SELECT c.id , c.name, c.registration_date FROM vcv.candidate c where c.vacancy = :vacancy order by c.id", nativeQuery = true)
    List<CandidateDto> all(@Param("vacancy") String vacancy);
}
