package com.hongming_academic_map.hongming_academic_map.dto;

import com.hongming_academic_map.hongming_academic_map.model.Paper;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class SearchResult {
    private Page<Paper> papers;
    private long totalResults;
    private int currentPage;
    private int totalPages;
}