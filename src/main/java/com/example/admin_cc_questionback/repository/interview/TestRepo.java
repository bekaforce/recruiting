package com.example.admin_cc_questionback.repository.interview;

import com.example.admin_cc_questionback.entities.interview.Test;
import com.example.admin_cc_questionback.entities.dtos.ResultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    @Query(value = "select 'Правильных' as name, count(*) as amount from vcv.test t where t.correct = 'true' and t.candidate_id = :candidate_id and t.key = 'true' " +
            "union all " +
            "select 'Всего' as name, count(*) as amount from vcv.test t where t.candidate_id = :candidate_id and t.key = 'true' ", nativeQuery = true)
    List<ResultDto> result(@Param(value = "candidate_id") Long candidate_id);
}
