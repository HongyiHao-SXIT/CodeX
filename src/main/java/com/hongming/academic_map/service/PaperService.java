package com.hongming.academic_map.service;

import com.hongming.academic_map.dto.PaperDto;
import com.hongming.academic_map.entity.Paper;
import com.hongming.academic_map.entity.User;
import com.hongming.academic_map.entity.UserPaperSave;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.hongming.academic_map.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class PaperService {
    private final PaperRepository paperRepository;
    private final UserPaperSaveRepository userPaperSaveRepository;

    public PaperService(PaperRepository paperRepository, UserPaperSaveRepository userPaperSaveRepository) {
        this.paperRepository = paperRepository;
        this.userPaperSaveRepository = userPaperSaveRepository;
    }

    public PaperDto getPaperDetails(String paperId) {
        Paper paper = paperRepository.findById(paperId).orElseThrow();
        
        PaperDto paperDto = new PaperDto();
        paperDto.setPaper(paper);
        
        // Check if current user has saved this paper
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            paperDto.setSaved(userPaperSaveRepository.existsByUserAndPaperId(user, paperId));
        }
        
        // Get related papers (simplified - in real app would use more sophisticated algorithm)
        paperDto.setRelatedPapers(paperRepository.findTop5ByJournalOrderByCitationsDesc(paper.getJournal()));
        
        // Generate citation formats
        paperDto.setApaCitation(generateApaCitation(paper));
        paperDto.setMlaCitation(generateMlaCitation(paper));
        paperDto.setChicagoCitation(generateChicagoCitation(paper));
        
        return paperDto;
    }

    public void toggleSavePaper(String paperId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        
        User user = (User) authentication.getPrincipal();
        Optional<UserPaperSave> existingSave = userPaperSaveRepository.findByUserAndPaperId(user, paperId);
        
        if (existingSave.isPresent()) {
            userPaperSaveRepository.delete(existingSave.get());
        } else {
            Paper paper = paperRepository.findById(paperId).orElseThrow();
            UserPaperSave newSave = new UserPaperSave();
            newSave.setUser(user);
            newSave.setPaper(paper);
            userPaperSaveRepository.save(newSave);
        }
    }

    private String generateApaCitation(Paper paper) {
        // Simplified APA citation format
        return String.format("%s (%d). %s. %s, %s.", 
            String.join(", ", paper.getAuthors()), 
            paper.getYear(),
            paper.getTitle(),
            paper.getJournal(),
            paper.getPages());
    }

    private String generateMlaCitation(Paper paper) {
        // Simplified MLA citation format
        return String.format("%s. \"%s.\" %s, vol. %s, no. %s, %d, pp. %s.", 
            String.join(", ", paper.getAuthors()),
            paper.getTitle(),
            paper.getJournal(),
            paper.getVolume(),
            paper.getIssue(),
            paper.getYear(),
            paper.getPages());
    }

    private String generateChicagoCitation(Paper paper) {
        // Simplified Chicago citation format
        return String.format("%s. \"%s.\" %s %s, no. %s (%d): %s.", 
            String.join(", ", paper.getAuthors()),
            paper.getTitle(),
            paper.getJournal(),
            paper.getVolume(),
            paper.getIssue(),
            paper.getYear(),
            paper.getPages());
    }
}