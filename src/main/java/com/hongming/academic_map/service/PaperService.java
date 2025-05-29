package com.hongming.academic_map.service;

import com.hongming.academic_map.model.Paper;
import com.hongming.academic_map.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;

    public List<Paper> searchPapers(String keyword) {
        return paperRepository.searchByKeyword(keyword);
    }

    public Optional<Paper> getPaperById(Long id) {
        return paperRepository.findById(id);
    }
}
