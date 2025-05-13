package com.hongming_academic_map.hongming_academic_map.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List; 
@Data
public class PaperUploadDto {
    private String title;
    private String abstractText;
    private List<String> authors;
    private List<String> keywords;
    private String field;
    private MultipartFile file;
}