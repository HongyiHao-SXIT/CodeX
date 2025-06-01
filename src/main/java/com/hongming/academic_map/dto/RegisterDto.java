package com.hongming.academic_map.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class RegisterDto {
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;
    
    @NotBlank
    @Size(min = 8)
    private String password;
    
    @NotBlank
    private String confirmPassword;
}