package com.hongming_academic_map.hongming_academic_map.controller;

import com.hongming_academic_map.hongming_academic_map.entity.Paper;
import com.hongming_academic_map.hongming_academic_map.service.PaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/papers")
public class PaperController {

    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("paper", new Paper());
        return "upload-paper";
    }

    @PostMapping("/upload")
    public String uploadPaper(@ModelAttribute Paper paper, 
                              @RequestParam("file") MultipartFile file,
                              Model model) {
        try {
            paperService.savePaper(paper, file);
            model.addAttribute("success", "Paper uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to upload paper: " + e.getMessage());
        }
        return "upload-paper";
    }

    @GetMapping("/search")
    public String searchPapers(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("papers", paperService.searchPapers(keyword));
        model.addAttribute("keyword", keyword);
        return "search-results";
    }

    @GetMapping("/download/{id}")
    public String downloadPaper(@PathVariable Long id, Model model) {
        Paper paper = paperService.getPaperById(id);
        if (paper != null) {
            model.addAttribute("paper", paper);
            return "download";
        } else {
            model.addAttribute("error", "Paper not found");
            return "search-results";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePaper(@PathVariable Long id, Model model) {
        paperService.deletePaper(id);
        model.addAttribute("success", "Paper deleted successfully");
        model.addAttribute("papers", paperService.getAllPapers());
        return "search-results";
    }
}    