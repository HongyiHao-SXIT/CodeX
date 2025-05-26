package com.codex.codex.repository;

import com.codex.codex.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByCourseId(Long courseId);
    Grade findByStudentIdAndCourseId(Long studentId, Long courseId);
}
