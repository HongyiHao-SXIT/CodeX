package com.hongming.academic_map.controller;

import com.hongming.academic_map.dto.PaperDto;
import com.hongming.academic_map.service.PaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaperController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/paper/{id}")
    public String getPaper(@PathVariable String id, Model model) {
        PaperDto paperDto = paperService.getPaperDetails(id);
        model.addAttribute("paper", paperDto.getPaper());
        model.addAttribute("saved", paperDto.isSaved());
        model.addAttribute("relatedPapers", paperDto.getRelatedPapers());
        model.addAttribute("apaCitation", paperDto.getApaCitation());
        model.addAttribute("mlaCitation", paperDto.getMlaCitation());
        model.addAttribute("chicagoCitation", paperDto.getChicagoCitation());
        
        return "paper-detail";
    }

    @PostMapping("/paper/{id}/save")
    public String toggleSavePaper(@PathVariable String id) {
        paperService.toggleSavePaper(id);
        return "redirect:/paper/" + id;
    }
}