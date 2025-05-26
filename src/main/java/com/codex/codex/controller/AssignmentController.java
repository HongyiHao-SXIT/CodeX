package com.codex.codex.controller;

import com.codex.codex.entity.Assignment;
import com.codex.codex.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentRepository assignmentRepository;

    @GetMapping
    public String listAssignments(Model model) {
        model.addAttribute("assignments", assignmentRepository.findAll());
        return "assignments";
    }

    @GetMapping("/course/{courseId}")
    public String assignmentsByCourse(@PathVariable Long courseId, Model model) {
        List<Assignment> list = assignmentRepository.findByCourseId(courseId);
        model.addAttribute("assignments", list);
        return "assignments";
    }
}
