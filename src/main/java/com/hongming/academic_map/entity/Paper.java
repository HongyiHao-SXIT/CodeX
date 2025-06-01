package com.hongming.academic_map.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "papers")
@Data
@NoArgsConstructor
public class Paper {
    @Id
    private String id; // DOI or other unique identifier
    
    private String title;
    private String abstractText;
    private String journal;
    private Integer year;
    private Integer citations;
    private String publishDate;
    private String volume;
    private String issue;
    private String pages;
    private String language;
    private String issn;
    private String pdfUrl;
    private String sourceUrl;
    
    @ElementCollection
    private List<String> authors;
    
    @ElementCollection
    private List<String> keywords;
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private List<UserPaperSave> savedByUsers;
}