package com.beeline.cc_question.repos.user;

import com.beeline.cc_question.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
