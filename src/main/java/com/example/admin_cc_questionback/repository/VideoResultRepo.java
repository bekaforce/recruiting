package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.VideoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoResultRepo extends JpaRepository<VideoResult, Long> {
    @Query(value = "select * from vcv.video_result vr where vr.id = :id", nativeQuery = true)
    VideoResult getVideoResultById(@Param("id") Long id);
}
