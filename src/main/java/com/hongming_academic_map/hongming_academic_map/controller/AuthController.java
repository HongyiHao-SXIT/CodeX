package com.hongming_academic_map.hongming_academic_map.controller;

import com.hongming_academic_map.hongming_academic_map.dto.UserDto;
import com.hongming_academic_map.hongming_academic_map.model.User;
import com.hongming_academic_map.hongming_academic_map.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        // 将 UserDto 转换为 User
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setInstitution(userDto.getInstitution());
        user.setResearchField(userDto.getResearchField());
        user.setPassword(userDto.getPassword());

        return ResponseEntity.ok(userService.registerUser(user));
    }
}