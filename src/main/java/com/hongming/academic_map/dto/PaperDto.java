package com.hongming.academic_map.dto;

import com.hongming.academic_map.entity.Paper;
import lombok.Data;

import java.util.List;

@Data
public class PaperDto {
    private Paper paper;
    private boolean saved;
    private List<Paper> relatedPapers;
    private String apaCitation;
    private String mlaCitation;
    private String chicagoCitation;
}