package com.hongming_academic_map.hongming_academic_map.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class SearchRequest {
    private String query;
    private String author;
    private String keyword;
    private String field;
    private Pageable pageable;
}