package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Role;
import com.beeline.cc_question.repos.RoleRepo;
import com.beeline.cc_question.services.RoleService;
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
