package com.hongming_academic_map.hongming_academic_map.repository;

import com.hongming_academic_map.hongming_academic_map.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    
    // 检查角色是否存在
    boolean existsByName(String name);
}