package com.codex.codex.controller;

import com.codex.codex.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeRepository gradeRepository;

    @GetMapping("/student/{studentId}")
    public String gradesByStudent(@PathVariable Long studentId, Model model) {
        model.addAttribute("grades", gradeRepository.findByStudentId(studentId));
        return "grades";
    }
}
