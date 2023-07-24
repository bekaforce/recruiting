package com.example.admin_cc_questionback.repository.candidate;

import com.example.admin_cc_questionback.entities.dtos.GetCandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.dtos.GetStageAnalyticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate findCandidateById(Long id);
    List<Candidate> findAllByCandidateTypeId(Long candidateType_id);
    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage = 'completed' and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> all(@Param("candidate_type_id") Long candidate_type_id);

    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage = 'failed' and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> allFailed(@Param("candidate_type_id") Long candidate_type_id);

    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage in ('choice','video','essay') and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> allOnInterview(@Param("candidate_type_id") Long candidate_type_id);

    @Query(value = "SELECT c.id , c.name, c.surname, c.status, c.registration_date FROM vcv.candidate c where c.candidate_type_id = :candidate_type_id and c.stage = 'testing' and c.is_archive is false order by c.id desc", nativeQuery = true)
    List<GetCandidateDto> allOnTest(@Param("candidate_type_id") Long candidate_type_id);

    @Query(value = "select ct.candidate_type as candidateType, count(ct.candidate_type) as overall,  \n" +
            "count(case when c.stage = 'testing' then c.stage else null end) as testing,\n" +
            "count(case when c.stage = 'failed' then c.stage else null end) as failed,\n" +
            "count(ct.candidate_type) - count(case when c.stage = 'testing' then c.stage else null end) - count(case when c.stage = 'failed' then c.stage else null end) as passed,\n" +
            "count(case when c.stage = 'choice' then c.stage else null end) as choice,\n" +
            "count(case when c.stage = 'essay' then c.stage else null end) as essay,\n" +
            "count(case when c.stage = 'video' then c.stage else null end) as video,\n" +
            "count(case when c.stage = 'completed' then c.stage else null end) as completed, \n" +
            "count(case when c.status = 'Приглашен' then c.stage else null end) as invited \n" +
            "from vcv.candidate c, vcv.candidate_type ct\n" +
            "where c.candidate_type_id = ct.id \n" +
            "and c.is_archive is false\n" +
            "and candidate_type_id = :candidate_type_id " +
            "and c.registration_date\n" +
            "between :start and  :end \n" +
            "group by ct.candidate_type", nativeQuery = true)
    List<GetStageAnalyticsDto> allStageAnalyticsByIdAndDate(@Param("candidate_type_id") Long candidate_type_id, @Param("start")LocalDateTime start, @Param("end") LocalDateTime end);

}
