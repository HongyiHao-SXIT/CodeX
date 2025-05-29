package com.hongming_academic_map.hongming_academic_map.service.impl;

import com.hongming_academic_map.hongming_academic_map.config.FileStorageProperties;
import com.hongming_academic_map.hongming_academic_map.entity.Paper;
import com.hongming_academic_map.hongming_academic_map.entity.User;
import com.hongming_academic_map.hongming_academic_map.repository.PaperRepository;
import com.hongming_academic_map.hongming_academic_map.service.PaperService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hongming_academic_map.hongming_academic_map.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class PaperServiceImpl implements PaperService {

    private final PaperRepository paperRepository;
    private final UserService userService;
    private final Path fileStorageLocation;

    public PaperServiceImpl(PaperRepository paperRepository, UserService userService, 
                            FileStorageProperties fileStorageProperties) {
        this.paperRepository = paperRepository;
        this.userService = userService;
        
        // 确保fileStorageProperties不为null
        if (fileStorageProperties == null) {
            throw new IllegalArgumentException("FileStorageProperties cannot be null");
        }
        
        // 使用更安全的方式创建路径
        String uploadDir = fileStorageProperties.getUploadDir();
        if (uploadDir == null || uploadDir.trim().isEmpty()) {
            uploadDir = "./uploads"; // 备用默认值
        }
        
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public void savePaper(Paper paper, MultipartFile file) throws IOException {
        if (paper == null) {
            throw new IllegalArgumentException("Paper cannot be null");
        }
        
        String username = getCurrentUsername();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        
        paper.setUploadedBy(user);

        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            paper.setFilePath(fileName);
        }

        paperRepository.save(paper);
    }

    @Override
    public List<Paper> searchPapers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllPapers();
        }
        return paperRepository.searchByKeyword(keyword);
    }

    @Override
    public List<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    @Override
    public Paper getPaperById(Long id) {
        if (id == null) {
            return null;
        }
        return paperRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePaper(Long id) {
        if (id == null) {
            return;
        }
        
        Paper paper = paperRepository.findById(id).orElse(null);
        if (paper != null && paper.getFilePath() != null) {
            try {
                Path filePath = this.fileStorageLocation.resolve(paper.getFilePath());
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                // 记录错误但不阻止数据库操作
                e.printStackTrace();
            }
        }
        paperRepository.deleteById(id);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}