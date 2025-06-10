# HongMing Academic Map

## Project Introduction
HongMing Academic Map is an academic paper management system built on Spring Boot. It provides functions such as user authentication, paper uploading, downloading, and deletion requests, facilitating users to manage and browse academic papers. The system uses MySQL as the database, combined with the Thymeleaf template engine and the Bootstrap framework, to provide a simple and user-friendly interface.

## Functional Features
- **User Authentication**: Supports user login and registration to ensure the security of the system.
- **Paper Management**: Users can upload academic papers and view all uploaded papers.
- **Paper Search**: Provides a keyword search function to help users quickly find the papers they need.
- **Paper Download**: Users can download papers of interest.
- **Deletion Request**: Users can request to delete the papers they uploaded.

## Technology Stack
- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, Bootstrap, jQuery
- **Database**: MySQL
- **Build Tool**: Maven

## Environment Requirements
- Java 21
- MySQL database
- Maven 3.9.9

## Installation and Configuration
### 1. Clone the Project
```bash
git clone https://github.com/HongyiHao-SXIT/HongMing_Academic_Map.git
cd HongMing_Academic_Map
```

### 2. Database Configuration
Create a database named `academic` and execute the `academic.sql` file to create the required tables:
```sql
CREATE DATABASE IF NOT EXISTS academic;
USE academic;

-- Execute the table creation statements in the academic.sql file
```

### 3. Modify the Configuration File
Open the `src/main/resources/application.properties` file and modify the following content according to your database configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/academic?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=123456
```

### 4. Build the Project
Build the project using Maven:
```bash
mvn clean package
```

### 5. Run the Project
```bash
mvn spring-boot:run
```

## Project Structure
```
HongMing_Academic_Map
├── .gitignore
├── .mvn
│   └── wrapper
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── academic.sql
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── hongming
    │   │           └── academic_map
    │   │               ├── AcademicMapApplication.java
    │   │               ├── model
    │   │               │   └── Paper.java
    │   │               ├── repository
    │   │               │   └── PaperRepository.java
    │   │               └── service
    │   │                   ├── PaperService.java
    │   │                   └── UserService.java
    │   ├── resources
    │   │   ├── application.properties
    │   │   ├── static
    │   │   │   ├── css
    │   │   │   │   ├── bootstrap.css
    │   │   │   │   └── bootstrap-theme.css
    │   │   │   └── js
    │   │   │       └── main.js
    │   │   └── templates
    │   │       ├── login.html
    │   │       └── papers.html
    └── test
        └── java
            └── com
                └── hongming
                    └── academic_map
                        └── AcademicMapApplicationTests.java
```

## Code Examples
### Paper Entity Class
```java
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
```

### Paper Repository Interface
```java
package com.hongming.academic_map.repository;

import com.hongming.academic_map.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findByUploaderUsername(String username);

    @Query(value = "SELECT * FROM paper " +
                   "WHERE LOWER(abstract_text) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                   "OR LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                   "OR LOWER(author) LIKE LOWER(CONCAT('%', :keyword, '%'))", 
           nativeQuery = true)
    List<Paper> searchByKeyword(@Param("keyword") String keyword);
}
```

### Paper Service Class
```java
package com.hongming.academic_map.service;

import com.hongming.academic_map.model.Paper;
import com.hongming.academic_map.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;

    public List<Paper> searchPapers(String keyword) {
        return paperRepository.searchByKeyword(keyword);
    }

    public Optional<Paper> getPaperById(Long id) {
        return paperRepository.findById(id);
    }
}
```

## Contribution Guidelines
If you want to contribute to this project, please follow these steps:
1. Fork this repository.
2. Create a new branch: `git checkout -b feature/your-feature`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push your branch: `git push origin feature/your-feature`.
5. Open a Pull Request.

## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Contact Information
If you have any questions or suggestions, please feel free to contact the project author.
