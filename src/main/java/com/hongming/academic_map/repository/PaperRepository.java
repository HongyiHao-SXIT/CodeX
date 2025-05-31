package com.hongming.academic_map.repository;

import com.hongming.academic_map.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findByUploaderUsername(String username);

    @Query(value = "SELECT * FROM paper " +
                   "WHERE abstract_text_lower LIKE CONCAT('%', :keyword, '%') " +
                   "OR title_lower LIKE CONCAT('%', :keyword, '%') " +
                   "OR author_lower LIKE CONCAT('%', :keyword, '%')", 
           nativeQuery = true)
    List<Paper> searchByKeyword(@Param("keyword") String keyword);
}