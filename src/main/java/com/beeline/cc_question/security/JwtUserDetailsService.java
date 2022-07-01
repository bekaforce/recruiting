package com.beeline.cc_question.security;

import com.beeline.cc_question.entities.user.User;
import com.beeline.cc_question.security.jwt.JwtUserFactory;
import com.beeline.cc_question.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);
        if (user == null){
             throw new UsernameNotFoundException("User with username: " + userName + " was not found");
         }

        return JwtUserFactory.create(user);
    }
}
