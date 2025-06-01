package com.hongming.academic_map.service;

import com.hongming.academic_map.dto.SearchResultDto;
import com.hongming.academic_map.entity.Paper;
import com.hongming.academic_map.repository.PaperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final PaperRepository paperRepository;

    public SearchService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public SearchResultDto search(String query, int page, int size) {
        long startTime = System.currentTimeMillis();
        
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Paper> paperPage = paperRepository.search(query, pageable);
        
        double searchTime = (System.currentTimeMillis() - startTime) / 1000.0;
        
        SearchResultDto result = new SearchResultDto();
        result.setQuery(query);
        result.setResults(paperPage.getContent());
        result.setTotalResults(paperPage.getTotalElements());
        result.setSearchTime(searchTime);
        result.setCurrentPage(page);
        result.setTotalPages(paperPage.getTotalPages());
        result.setSortOption("相关度");
        
        return result;
    }

    public SearchResultDto advancedSearch(String query, String author, Integer year, String type, int page, int size) {
        long startTime = System.currentTimeMillis();
        
        List<Paper> results = paperRepository.advancedSearch(author, year, type);
        double searchTime = (System.currentTimeMillis() - startTime) / 1000.0;
        
        SearchResultDto result = new SearchResultDto();
        result.setQuery(query);
        result.setResults(results);
        result.setTotalResults(results.size());
        result.setSearchTime(searchTime);
        result.setCurrentPage(1);
        result.setTotalPages(1);
        result.setSortOption("相关度");
        
        return result;
    }
}