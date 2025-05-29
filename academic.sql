CREATE DATABASE IF NOT EXISTS academic;
USE academic;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER',
    email VARCHAR(255),
    school VARCHAR(255),
    major VARCHAR(255)
);

CREATE TABLE paper (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    filename VARCHAR(255) NOT NULL,
    deletion_requested BOOLEAN DEFAULT FALSE,
    abstract_text TEXT,
    uploader_id BIGINT,
    CONSTRAINT fk_uploader FOREIGN KEY (uploader_id) REFERENCES user(id) ON DELETE SET NULL
);
