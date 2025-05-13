package com.hongming_academic_map.hongming_academic_map.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String institution;
    private String researchField;
    private List<String> roles;
}