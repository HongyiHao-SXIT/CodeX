CREATE DATABASE IF NOT EXISTS academic;
USE academic;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS papers (
    id VARCHAR(100) PRIMARY KEY,
    title TEXT NOT NULL,
    abstract_text TEXT,
    journal VARCHAR(255),
    year INT,
    citations INT DEFAULT 0,
    publish_date VARCHAR(50),
    volume VARCHAR(20),
    issue VARCHAR(20),
    pages VARCHAR(50),
    language VARCHAR(30),
    issn VARCHAR(20),
    pdf_url VARCHAR(255),
    source_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS paper_authors (
    paper_id VARCHAR(100) NOT NULL,
    author VARCHAR(255) NOT NULL,
    PRIMARY KEY (paper_id, author),
    FOREIGN KEY (paper_id) REFERENCES papers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS paper_keywords (
    paper_id VARCHAR(100) NOT NULL,
    keyword VARCHAR(255) NOT NULL,
    PRIMARY KEY (paper_id, keyword),
    FOREIGN KEY (paper_id) REFERENCES papers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_paper_saves (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    paper_id VARCHAR(100) NOT NULL,
    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (paper_id) REFERENCES papers(id) ON DELETE CASCADE,
    UNIQUE KEY (user_id, paper_id)
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);

CREATE INDEX idx_papers_title ON papers(title(255));
CREATE INDEX idx_papers_journal ON papers(journal);
CREATE INDEX idx_papers_year ON papers(year);
CREATE INDEX idx_papers_citations ON papers(citations);

CREATE INDEX idx_paper_authors_author ON paper_authors(author(255));

CREATE INDEX idx_paper_keywords_keyword ON paper_keywords(keyword);

CREATE INDEX idx_user_paper_saves_user ON user_paper_saves(user_id);
CREATE INDEX idx_user_paper_saves_paper ON user_paper_saves(paper_id);