package com.hongming.academic_map.controller;

import com.hongming.academic_map.dto.LoginDto;
import com.hongming.academic_map.dto.RegisterDto;
import com.hongming.academic_map.entity.User;
import com.hongming.academic_map.service.AuthService;
import com.hongming.academic_map.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") @Valid LoginDto loginDto, 
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        
        try {
            authService.login(loginDto);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDto") @Valid RegisterDto registerDto, 
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
        try {
            userService.register(registerDto);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}