package com.hongming_academic_map.hongming_academic_map.repository;

import com.hongming_academic_map.hongming_academic_map.model.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

    // 根据标题搜索论文(模糊匹配)
    @Query("SELECT p FROM Paper p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Paper> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);

    // 根据作者搜索论文
    @Query("SELECT p FROM Paper p JOIN p.authors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :author, '%'))")
    Page<Paper> findByAuthorNameContaining(@Param("author") String author, Pageable pageable);

    // 根据关键词搜索论文
    @Query("SELECT p FROM Paper p JOIN p.keywords k WHERE LOWER(k.keyword) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Paper> findByKeywordContaining(@Param("keyword") String keyword, Pageable pageable);

    // 获取用户上传的所有论文
    Page<Paper> findByUploaderId(Long userId, Pageable pageable);

    // 获取某领域的最新论文
    Page<Paper> findByFieldOrderByPublicationDateDesc(String field, Pageable pageable);

    // 获取高引用论文
    Page<Paper> findByCitationsGreaterThanOrderByCitationsDesc(int minCitations, Pageable pageable);
}