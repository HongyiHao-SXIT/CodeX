package com.hongming.academic_map.dto;

import com.hongming.academic_map.entity.Paper;
import lombok.Data;

import java.util.List;

@Data
public class SearchResultDto {
    private String query;
    private List<Paper> results;
    private long totalResults;
    private double searchTime;
    private int currentPage;
    private int totalPages;
    private String sortOption;

    private String searchType;
    private List<String> suggestions;
}