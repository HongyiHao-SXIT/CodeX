package com.hongming_academic_map.hongming_academic_map.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PaperDto {
    private Long id;
    private String title;
    private String abstractText;
    private List<String> authors;
    private List<String> keywords;
    private String field;
    private LocalDate publicationDate;
    private int citations;
    private Long uploaderId;
}