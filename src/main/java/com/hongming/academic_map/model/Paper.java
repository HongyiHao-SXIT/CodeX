package com.hongming.academic_map.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String filename;

    private boolean deletionRequested;
@Lob
@Column(columnDefinition = "TEXT")
    private String abstractText;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private User uploader;
}
