package com.hongming_academic_map.hongming_academic_map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.hongming_academic_map.hongming_academic_map.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class HongmingAcademicMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(HongmingAcademicMapApplication.class, args);
    }
    
}