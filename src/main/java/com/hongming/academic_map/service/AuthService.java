package com.hongming.academic_map.service;

import com.hongming.academic_map.dto.LoginDto;
import com.hongming.academic_map.entity.User;
import com.hongming.academic_map.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userRepository.findByUsername(loginDto.getUsername()).orElseThrow();
    }
}