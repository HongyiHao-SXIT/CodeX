package com.hongming.academic_map.service;

import com.hongming.academic_map.dto.RegisterDto;
import com.hongming.academic_map.entity.User;
import com.hongming.academic_map.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        
        return userRepository.save(user);
    }
}