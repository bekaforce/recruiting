package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.VideoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoResultRepo extends JpaRepository<VideoResult, Long> {
}
