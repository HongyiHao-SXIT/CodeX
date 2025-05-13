package com.hongming_academic_map.hongming_academic_map.controller;

import com.hongming_academic_map.hongming_academic_map.dto.UserDto;
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
        return ResponseEntity.ok(userService.registerUser(userDto));
    }
}