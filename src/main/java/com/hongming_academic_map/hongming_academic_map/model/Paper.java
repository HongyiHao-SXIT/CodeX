package com.hongming_academic_map.hongming_academic_map.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String abstractText;
    
    @ElementCollection
    private List<String> authors;
    
    @ElementCollection
    private List<String> keywords;
    
    private String field;
    private LocalDate publicationDate;
    private int citations;
    private String filePath;
    
    @ManyToOne
    @JoinColumn(name = "uploader_id")
    @JsonIgnore
    private User uploader;
}