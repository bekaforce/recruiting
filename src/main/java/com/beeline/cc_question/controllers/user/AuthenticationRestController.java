package com.beeline.cc_question.controllers.user;

import com.beeline.cc_question.controllers.Url;
import com.beeline.cc_question.entities.dtos.user.AuthenticationRequestDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.user.User;
import com.beeline.cc_question.security.jwt.JwtTokenProvider;
import com.beeline.cc_question.services.user.UserService;
import com.beeline.cc_question.services.candidate.impl.CandidateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.USER)
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final CandidateServiceImpl candidateService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, CandidateServiceImpl candidateService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.candidateService = candidateService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            Candidate candidate = candidateService.candidateByEmail(username);
            if (user == null){
                return new ResponseEntity<>("Пользоветель с логином " + username + " не найден", HttpStatus.NOT_FOUND);
            }
            if (candidateService.expiration(candidate.getRegistrationDate())){
                return new ResponseEntity<>("Время прохождения онлайн собеседования прошло, просьба начать с начала", HttpStatus.UNAUTHORIZED);
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            return userService.response(username, token);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Неверный логин или пароль", HttpStatus.UNAUTHORIZED);
        }
    }
}
