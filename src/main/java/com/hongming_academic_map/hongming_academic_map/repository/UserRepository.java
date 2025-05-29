package com.hongming_academic_map.hongming_academic_map.repository;

import com.hongming_academic_map.hongming_academic_map.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}    