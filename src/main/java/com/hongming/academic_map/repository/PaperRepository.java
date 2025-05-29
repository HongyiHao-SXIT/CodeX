package com.hongming.academic_map.repository;

import com.hongming.academic_map.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    @Query("SELECT p FROM Paper p WHERE p.title LIKE %:keyword% OR p.author LIKE %:keyword%")
    List<Paper> searchByKeyword(@Param("keyword") String keyword);
}
