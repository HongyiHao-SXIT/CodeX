package com.hongming_academic_map.hongming_academic_map.repository;

import com.hongming_academic_map.hongming_academic_map.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 通过邮箱查找用户(用于登录)
    Optional<User> findByEmail(String email);

    // 检查邮箱是否已存在(用于注册)
    boolean existsByEmail(String email);

    // 通过用户名查找用户
    Optional<User> findByUsername(String username);

    // 检查用户名是否已存在
    boolean existsByUsername(String username);

    // 搜索用户(用于管理员功能)
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<User> searchUsers(@Param("query") String query, Pageable pageable);

    // 查找拥有特定角色的用户
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    Page<User> findByRoleName(@Param("roleName") String roleName, Pageable pageable);
}