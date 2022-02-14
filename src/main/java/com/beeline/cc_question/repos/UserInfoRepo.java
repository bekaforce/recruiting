package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUserId(String userId);
}
