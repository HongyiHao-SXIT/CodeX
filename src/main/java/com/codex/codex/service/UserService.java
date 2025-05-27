package com.codex.codex.service;

import com.codex.codex.entity.User;
import com.codex.codex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String register(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            return "The username already exists";
        }
        if (userRepository.existsByEmail(email)) {
            return "The mailbox already exists";
        }
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = User.builder()
                .username(username)
                .email(email)
                .password(hashed)
                .role("STUDENT")
                .build();
        userRepository.save(user);
        return null;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;
        if (!BCrypt.checkpw(password, user.getPassword())) {
            return null;
        }
        return user;
    }
}
