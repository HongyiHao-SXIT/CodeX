package com.hongming_academic_map.hongming_academic_map.controller;

import com.hongming_academic_map.hongming_academic_map.dto.PaperDto;
import com.hongming_academic_map.hongming_academic_map.dto.PaperUploadDto;
import com.hongming_academic_map.hongming_academic_map.service.PaperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping
    public Page<PaperDto> getAllPapers(Pageable pageable) {
        return paperService.getAllPapers(pageable);
    }

    @PostMapping("/upload")
    public ResponseEntity<PaperDto> uploadPaper(@ModelAttribute PaperUploadDto paperUploadDto, 
                                              Authentication authentication) {
        return ResponseEntity.ok(paperService.uploadPaper(paperUploadDto, authentication));
    }
}