package com.hongming_academic_map.hongming_academic_map.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Value("${app.upload.dir:${user.home}/.academic-uploads}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}