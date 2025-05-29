package com.hongming_academic_map.hongming_academic_map.service;

import com.hongming_academic_map.hongming_academic_map.entity.Paper;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface PaperService {
    void savePaper(Paper paper, MultipartFile file) throws IOException;
    List<Paper> searchPapers(String keyword);
    List<Paper> getAllPapers();
    Paper getPaperById(Long id);
    void deletePaper(Long id);
}    