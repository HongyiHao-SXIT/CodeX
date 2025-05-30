package com.hongming.academic_map.controller;

import com.hongming.academic_map.model.Paper;
import com.hongming.academic_map.model.User;
import com.hongming.academic_map.repository.PaperRepository;
import com.hongming.academic_map.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PaperController {

    private final PaperRepository paperRepository;
    private final UserRepository userRepository;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @GetMapping("/papers")
    public String listPapers(Model model) {
        List<Paper> papers = paperRepository.findAll();
        model.addAttribute("papers", papers);
        return "papers";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Paper> results = paperRepository.searchByKeyword(keyword.toLowerCase());
        model.addAttribute("papers", results);
        return "searchresult";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file,
                               @RequestParam("title") String title,
                               @RequestParam("author") String author,
                               @RequestParam("abstractText") String abstractText,
                               Authentication authentication) throws IOException {

        User uploader = userRepository.findByUsername(authentication.getName());

        String uniqueFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        String projectRoot = System.getProperty("user.dir");
        Path uploadPath = Paths.get(projectRoot, uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(uniqueFilename);
        file.transferTo(filePath.toFile());

        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setAuthor(author);
        paper.setAbstractText(abstractText);
        paper.setFilename(uniqueFilename);
        paper.setUploader(uploader);
        paper.setDeletionRequested(false);

        paperRepository.save(paper);

        return "redirect:/index";
    }

    @GetMapping("/download/{id}")
    public void downloadPaper(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Paper paper = paperRepository.findById(id).orElseThrow(() -> new FileNotFoundException("论文不存在"));

        String projectRoot = System.getProperty("user.dir");
        Path filePath = Paths.get(projectRoot, uploadDir).resolve(paper.getFilename());
        File file = filePath.toFile();

        if (file.exists()) {
            response.setContentType(Files.probeContentType(filePath));
            String originalFilename = paper.getFilename().substring(paper.getFilename().indexOf('_') + 1);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
            Files.copy(filePath, response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File could not found");
        }
    }

    @PostMapping("/request-delete/{id}")
    public String requestDelete(@PathVariable Long id, Authentication auth) {
        Paper paper = paperRepository.findById(id).orElseThrow();
        if (paper.getUploader().getUsername().equals(auth.getName())) {
            paper.setDeletionRequested(true);
            paperRepository.save(paper);
        }
        return "redirect:/papers";
    }
}