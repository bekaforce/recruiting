package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Role;
import com.beeline.cc_question.entities.Status;
import com.beeline.cc_question.entities.User;
import com.beeline.cc_question.repos.UserRepo;
import com.beeline.cc_question.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleServiceImpl roleService;

    public UserServiceImpl(UserRepo userRepo, RoleServiceImpl roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
    }


    @Override
    public User register(String email, String phoneNumber, String password, LocalDateTime time) {
        User user = userRepo.findByUsername(email);
        if (user != null){
            user.setPassword(password);
        }
        else {
            user = new User();
            user.setUsername(email);
            Role roleUser = roleService.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            user.setRoles(userRoles);
            user.setPassword(password);
            user.setStatus(Status.ACTIVE);
        }
        user.setPhoneNumber(phoneNumber);
        user.setDate(time);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
