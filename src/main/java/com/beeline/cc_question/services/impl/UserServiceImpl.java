package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.Role;
import com.beeline.cc_question.entities.Status;
import com.beeline.cc_question.entities.User;
import com.beeline.cc_question.repos.UserRepo;
import com.beeline.cc_question.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleServiceImpl roleService;
    private final CandidateServiceImpl candidateService;
    private final VideoResultServiceImpl videoResultService;

    public UserServiceImpl(UserRepo userRepo, RoleServiceImpl roleService, CandidateServiceImpl candidateService, VideoResultServiceImpl videoResultService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.candidateService = candidateService;
        this.videoResultService = videoResultService;
    }


    @Override
    public User register(String email, String phoneNumber, String password) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
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
        user.setDate(now);
        return userRepo.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> response(String username, String token) {
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        Candidate candidate = candidateService.candidateByEmail(username);
            response.put("stage", candidateService.getStage(candidate.getId()));
            response.put("candidateTypeId", candidate.getCandidateType().getId());
            response.put("candidateId", candidate.getId());
            String stage = candidateService.getStage(candidate.getId());
            if (stage.contains("video")){
                Long index = videoResultService.position(candidate.getId());
                response.put("index", index);
            }
            if (stage.contains("testing")){
                response.put("index", 0);
            }
        return ResponseEntity.ok(response);
    }
}
