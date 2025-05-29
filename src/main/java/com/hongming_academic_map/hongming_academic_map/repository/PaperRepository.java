package com.hongming_academic_map.hongming_academic_map.repository;

import com.hongming_academic_map.hongming_academic_map.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByTitleContainingIgnoreCase(String title);
    
    List<Paper> findByAuthorContainingIgnoreCase(String author);

    @Query("SELECT p FROM Paper p WHERE p.title LIKE %:keyword% OR p.author LIKE %:keyword% OR p.abstracts LIKE %:keyword%")
    List<Paper> searchByKeyword(@Param("keyword") String keyword);
}    