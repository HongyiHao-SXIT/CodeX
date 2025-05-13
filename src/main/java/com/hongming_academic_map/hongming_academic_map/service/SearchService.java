package com.hongming_academic_map.hongming_academic_map.service;

import com.hongming_academic_map.hongming_academic_map.dto.SearchRequest;
import com.hongming_academic_map.hongming_academic_map.dto.SearchResult;
import com.hongming_academic_map.hongming_academic_map.model.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hongming_academic_map.hongming_academic_map.repository.PaperRepository;

@Service
public class SearchService {

    private final PaperRepository paperRepository;

    public SearchService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public Page<Paper> searchPapers(String query, Pageable pageable) {
        return paperRepository.findByTitleContaining(query, pageable);
    }

    public Page<Paper> searchByAuthor(String author, Pageable pageable) {
        return paperRepository.findByAuthorNameContaining(author, pageable);
    }

    public Page<Paper> searchByKeyword(String keyword, Pageable pageable) {
        return paperRepository.findByKeywordContaining(keyword, pageable);
    }

    public Page<Paper> searchByField(String field, Pageable pageable) {
        return paperRepository.findByFieldOrderByPublicationDateDesc(field, pageable);
    }

    public Page<Paper> getTopCitedPapers(Pageable pageable) {
        return paperRepository.findByCitationsGreaterThanOrderByCitationsDesc(10, pageable);
    }

    public Page<Paper> advancedSearch(String title, String author, String keyword, String field, Pageable pageable) {
        if (title != null) {
            return searchPapers(title, pageable);
        } else if (author != null) {
            return searchByAuthor(author, pageable);
        } else if (keyword != null) {
            return searchByKeyword(keyword, pageable);
        } else if (field != null) {
            return searchByField(field, pageable);
        }
        return paperRepository.findAll(pageable);
    }

    public SearchResult search(SearchRequest searchRequest) {
        Page<Paper> papers = advancedSearch(
            searchRequest.getQuery(),
            searchRequest.getAuthor(),
            searchRequest.getKeyword(),
            searchRequest.getField(),
            searchRequest.getPageable()
        );

        SearchResult result = new SearchResult();
        result.setPapers(papers);
        result.setTotalResults(papers.getTotalElements());
        result.setCurrentPage(papers.getNumber());
        result.setTotalPages(papers.getTotalPages());

        return result;
    }
}