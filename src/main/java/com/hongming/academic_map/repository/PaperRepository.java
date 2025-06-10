package com.hongming.academic_map.repository;

import com.hongming.academic_map.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

    @Query("SELECT p FROM Paper p WHERE " +
           "LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.abstractText) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "EXISTS (SELECT a FROM p.authors a WHERE LOWER(a) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Paper> search(@Param("query") String query, Pageable pageable);
    
    @Query("SELECT p FROM Paper p WHERE " +
           "(:author IS NULL OR EXISTS (SELECT a FROM p.authors a WHERE LOWER(a) LIKE LOWER(CONCAT('%', :author, '%')))) AND " +
           "(:year IS NULL OR p.year = :year) AND " +
           "(:type IS NULL OR LOWER(p.journal) LIKE LOWER(CONCAT('%', :type, '%')))")
    Page<Paper> advancedSearch(
        @Param("author") String author,
        @Param("year") Integer year,
        @Param("type") String type,
        Pageable pageable
    );
    
    @Query("SELECT p FROM Paper p JOIN p.savedByUsers ups WHERE ups.user.id = :userId")
    Page<Paper> findSavedPapersByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT p FROM Paper p WHERE p.journal = :journal ORDER BY p.citations DESC")
    Page<Paper> findTop5PapersByJournal(@Param("journal") String journal, Pageable pageable);

    List<Paper> findTop5ByJournalOrderByCitationsDesc(String journal);
}