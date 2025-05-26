package com.codex.codex.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "video_url", nullable = false, length = 255)
    private String videoUrl;

    @Column(length = 100)
    private String title;
}
