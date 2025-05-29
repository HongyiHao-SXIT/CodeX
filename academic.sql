CREATE DATABASE academic;
use academic;
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    institution VARCHAR(100),
    major VARCHAR(100),
    register_time DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE papers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    abstract TEXT,
    author VARCHAR(100),
    file_path VARCHAR(255),
    upload_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    uploaded_by BIGINT,
    FOREIGN KEY (uploaded_by) REFERENCES users(id)
);