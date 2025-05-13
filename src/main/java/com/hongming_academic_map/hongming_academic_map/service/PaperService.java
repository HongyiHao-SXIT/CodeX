package com.hongming_academic_map.hongming_academic_map.service;

import com.hongming_academic_map.hongming_academic_map.exception.ResourceNotFoundException;
import com.hongming_academic_map.hongming_academic_map.model.Paper;
import com.hongming_academic_map.hongming_academic_map.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hongming_academic_map.hongming_academic_map.repository.PaperRepository;

import java.io.IOException;
import java.util.List;

@Service
public class PaperService {

    private final PaperRepository paperRepository;
    private final StorageService storageService;

    public PaperService(PaperRepository paperRepository, StorageService storageService) {
        this.paperRepository = paperRepository;
        this.storageService = storageService;
    }

    public Page<Paper> getAllPapers(Pageable pageable) {
        return paperRepository.findAll(pageable);
    }

    public Paper getPaperById(Long id) {
        return paperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paper", "id", id));
    }

    public Paper uploadPaper(Paper paper, MultipartFile file, User uploader) throws IOException {
        String filePath = storageService.store(file);
        paper.setFilePath(filePath);
        paper.setUploader(uploader);
        return paperRepository.save(paper);
    }

    public Paper updatePaper(Long id, Paper paperDetails) {
        Paper paper = getPaperById(id);
        paper.setTitle(paperDetails.getTitle());
        paper.setAbstractText(paperDetails.getAbstractText());
        paper.setAuthors(paperDetails.getAuthors());
        paper.setKeywords(paperDetails.getKeywords());
        paper.setPublicationDate(paperDetails.getPublicationDate());
        paper.setField(paperDetails.getField());
        return paperRepository.save(paper);
    }

    public void deletePaper(Long id) {
        Paper paper = getPaperById(id);
        storageService.delete(paper.getFilePath());
        paperRepository.delete(paper);
    }

    public Page<Paper> getUserPapers(Long userId, Pageable pageable) {
        return paperRepository.findByUploaderId(userId, pageable);
    }

    public Paper incrementCitations(Long paperId) {
        Paper paper = getPaperById(paperId);
        paper.setCitations(paper.getCitations() + 1);
        return paperRepository.save(paper);
    }
}