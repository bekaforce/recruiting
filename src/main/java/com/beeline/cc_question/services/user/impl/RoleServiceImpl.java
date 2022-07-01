package com.beeline.cc_question.services.user.impl;

import com.beeline.cc_question.entities.user.Role;
import com.beeline.cc_question.repos.user.RoleRepo;
import com.beeline.cc_question.services.user.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }
}
