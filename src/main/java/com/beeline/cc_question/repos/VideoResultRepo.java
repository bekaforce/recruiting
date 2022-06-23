package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.VideoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoResultRepo extends JpaRepository<VideoResult, Long> {
    @Query(value = "select max(v.position) from vcv.video_result v where v.candidate_id = :candidate_id", nativeQuery = true)
    Long position(@Param(value = "candidate_id") Long candidate_id);
}
