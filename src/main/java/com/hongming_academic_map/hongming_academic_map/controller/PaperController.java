package com.hongming_academic_map.hongming_academic_map.controller;

import com.hongming_academic_map.hongming_academic_map.dto.PaperDto;
import com.hongming_academic_map.hongming_academic_map.dto.PaperUploadDto;
import com.hongming_academic_map.hongming_academic_map.model.Paper;
import com.hongming_academic_map.hongming_academic_map.model.User;
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
        Page<Paper> papers = paperService.getAllPapers(pageable);
        return papers.map(this::convertToDto);
    }

    private PaperDto convertToDto(Paper paper) {
        PaperDto dto = new PaperDto();
        dto.setId(paper.getId());
        dto.setTitle(paper.getTitle());
        dto.setAbstractText(paper.getAbstractText());
        dto.setAuthors(paper.getAuthors());
        dto.setKeywords(paper.getKeywords());
        dto.setField(paper.getField());
        dto.setPublicationDate(paper.getPublicationDate());
        dto.setCitations(paper.getCitations());
        if (paper.getUploader() != null) {
            dto.setUploaderId(paper.getUploader().getId());
        }
        return dto;
    }

    @PostMapping("/upload")
    public ResponseEntity<PaperDto> uploadPaper(@ModelAttribute PaperUploadDto paperUploadDto, 
                                              Authentication authentication) {
        Paper paper = new Paper();
        paper.setTitle(paperUploadDto.getTitle());
        paper.setAbstractText(paperUploadDto.getAbstractText());
        paper.setAuthors(paperUploadDto.getAuthors());
        paper.setKeywords(paperUploadDto.getKeywords());
        paper.setField(paperUploadDto.getField());

        User uploader = (User) authentication.getPrincipal();

        try {
            Paper uploadedPaper = paperService.uploadPaper(paper, paperUploadDto.getFile(), uploader);
            PaperDto paperDto = convertToDto(uploadedPaper);
            return ResponseEntity.ok(paperDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}