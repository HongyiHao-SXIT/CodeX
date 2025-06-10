package com.hongming.academic_map.repository;

import com.hongming.academic_map.entity.User;
import com.hongming.academic_map.entity.UserPaperSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPaperSaveRepository extends JpaRepository<UserPaperSave, Long> {
    Optional<UserPaperSave> findByUserAndPaperId(User user, String paperId);
    boolean existsByUserAndPaperId(User user, String paperId);
}