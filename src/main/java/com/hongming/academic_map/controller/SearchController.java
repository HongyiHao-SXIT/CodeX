package com.hongming.academic_map.controller;

import com.hongming.academic_map.dto.SearchResultDto;
import com.hongming.academic_map.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String search(@RequestParam String q, 
                        @RequestParam(required = false, defaultValue = "1") int page,
                        Model model) {
        SearchResultDto result = searchService.search(q, page, 10);
        model.addAttribute("query", q);
        model.addAttribute("results", result.getResults());
        model.addAttribute("totalResults", result.getTotalResults());
        model.addAttribute("searchTime", result.getSearchTime());
        model.addAttribute("currentPage", result.getCurrentPage());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("sortOption", result.getSortOption());
        
        return "search-result";
    }

    @GetMapping("/advanced")
    public String advancedSearch(@RequestParam(required = false) String q,
                                @RequestParam(required = false) String author,
                                @RequestParam(required = false) Integer year,
                                @RequestParam(required = false) String type,
                                Model model) {
        SearchResultDto result = searchService.advancedSearch(q, author, year, type, 1, 10);
        model.addAttribute("query", q);
        model.addAttribute("results", result.getResults());
        model.addAttribute("totalResults", result.getTotalResults());
        model.addAttribute("searchTime", result.getSearchTime());
        model.addAttribute("currentPage", result.getCurrentPage());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("sortOption", result.getSortOption());
        
        return "search-result";
    }
}