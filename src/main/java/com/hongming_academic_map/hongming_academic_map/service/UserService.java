package com.hongming_academic_map.hongming_academic_map.service;

import com.hongming_academic_map.hongming_academic_map.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(User user);
    User findByUsername(String username);
    List<User> findAllUsers();
}    