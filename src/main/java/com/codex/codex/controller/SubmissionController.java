package com.codex.codex.controller;

import com.codex.codex.entity.Submission;
import com.codex.codex.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionRepository submissionRepository;

    @GetMapping("/assignment/{assignmentId}")
    public String submissionsForAssignment(@PathVariable Long assignmentId, Model model) {
        model.addAttribute("submissions", submissionRepository.findByAssignmentId(assignmentId));
        return "submissions";
    }

    @GetMapping("/student/{studentId}")
    public String submissionsByStudent(@PathVariable Long studentId, Model model) {
        model.addAttribute("submissions", submissionRepository.findByStudentId(studentId));
        return "submissions";
    }
}
