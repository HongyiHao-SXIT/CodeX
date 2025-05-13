package com.hongming_academic_map.hongming_academic_map.controller;

import com.hongming_academic_map.hongming_academic_map.dto.SearchRequest;
import com.hongming_academic_map.hongming_academic_map.dto.SearchResult;
import com.hongming_academic_map.hongming_academic_map.service.SearchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public SearchResult search(@RequestBody SearchRequest searchRequest) {
        return searchService.search(searchRequest);
    }
}