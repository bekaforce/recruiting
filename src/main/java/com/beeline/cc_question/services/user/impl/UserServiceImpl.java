package com.beeline.cc_question.services.user.impl;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.user.Role;
import com.beeline.cc_question.entities.candidate.Status;
import com.beeline.cc_question.entities.user.User;
import com.beeline.cc_question.repos.user.UserRepo;
import com.beeline.cc_question.services.candidate.impl.EncoderServiceImpl;
import com.beeline.cc_question.services.interview.impl.EssayServiceImpl;
import com.beeline.cc_question.services.interview.impl.VideoResultServiceImpl;
import com.beeline.cc_question.services.user.UserService;
import com.beeline.cc_question.services.candidate.impl.CandidateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
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
    private final EssayServiceImpl essayService;
    private final EncoderServiceImpl encoderService;

    public UserServiceImpl(UserRepo userRepo, RoleServiceImpl roleService, CandidateServiceImpl candidateService, VideoResultServiceImpl videoResultService, EssayServiceImpl essayService, EncoderServiceImpl encoderService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.candidateService = candidateService;
        this.videoResultService = videoResultService;
        this.essayService = essayService;
        this.encoderService = encoderService;
    }


    @Override
    public User register(String email, String phoneNumber, String password) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        String username = encoderService.encrypt(email);
        User user = userRepo.findUserByUsername(username);
        if (user != null){
            user.setPassword(password);
        }
        else {
            user = new User();
            user.setUsername(encoderService.encrypt(email));
            Role roleUser = roleService.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            user.setRoles(userRoles);
            user.setPassword(password);
            user.setStatus(Status.ACTIVE);
        }
        user.setPhoneNumber(encoderService.encrypt(phoneNumber));
        user.setDate(now);
        return userRepo.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepo.findUserByUsername(userName);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> response(String username, String token) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        Candidate candidate = candidateService.candidateByEmail(username);
            response.put("stage", candidateService.getStage(candidate));
            response.put("candidateTypeId", candidate.getCandidateType().getId());
            response.put("candidateTypeName", candidate.getCandidateType().getCandidateType());
            response.put("candidateId", candidate.getId());
            String stage = candidateService.getStage(candidate);
            if (stage.contains("video")){
                Long index = videoResultService.position(candidate.getId());
                response.put("index", index);
            }
            if (stage.contains("testing")){
                response.put("index", 0);
            }
            if (stage.contains("essay")){
                Long index = essayService.position(candidate.getId());
                response.put("index", index);
            }
        return ResponseEntity.ok(response);
    }
}
