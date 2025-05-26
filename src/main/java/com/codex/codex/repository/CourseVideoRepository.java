package com.codex.codex.repository;

import com.codex.codex.entity.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseVideoRepository extends JpaRepository<CourseVideo, Long> {
    List<CourseVideo> findByCourseId(Long courseId);
}
