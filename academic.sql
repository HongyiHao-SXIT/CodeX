CREATE DATABASE IF NOT EXISTS academic;
USE academic;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER',
    email VARCHAR(255),
    school VARCHAR(255),
    major VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS paper (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    filename VARCHAR(255) NOT NULL,
    deletion_requested BOOLEAN DEFAULT FALSE,
    abstract_text TEXT,
    uploader_id BIGINT,
    CONSTRAINT fk_uploader FOREIGN KEY (uploader_id) REFERENCES user(id) ON DELETE SET NULL
);

ALTER TABLE paper
ADD COLUMN abstract_text_lower VARCHAR(255) AS (LOWER(abstract_text)) VIRTUAL;

CREATE INDEX idx_paper_abstract_text ON paper (abstract_text_lower);

ALTER TABLE paper
ADD COLUMN title_lower VARCHAR(255) AS (LOWER(title)) VIRTUAL;
CREATE INDEX idx_paper_title ON paper (title_lower);

ALTER TABLE paper
ADD COLUMN author_lower VARCHAR(255) AS (LOWER(author)) VIRTUAL;

CREATE INDEX idx_paper_author ON paper (author_lower);
