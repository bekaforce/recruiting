package com.beeline.cc_question.services.user;

import com.beeline.cc_question.entities.user.Role;

public interface RoleService {
    Role findByName(String name);
}
