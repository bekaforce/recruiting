package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.UserInfo;
import com.beeline.cc_question.entities.VideoResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoResultRepo extends JpaRepository<VideoResult, Integer> {
    Optional<VideoResult> findByUserInfo(UserInfo userInfo);
}
