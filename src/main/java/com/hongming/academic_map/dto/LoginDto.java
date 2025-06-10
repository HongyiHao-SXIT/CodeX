package com.hongming.academic_map.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
}