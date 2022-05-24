package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.Role;

public interface RoleService {
    Role findByName(String name);
}
