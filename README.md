以下是为你的 **Spring Boot 在线代码学习平台** 项目生成的 GitHub 使用的 `README.md` 文档，风格清晰，内容完整，适合公开项目或课程作业展示。

---

## 📚 Online Code Learning Platform

An online CS-style platform for learning and practicing programming with videos, assignments, auto-grading, and performance tracking — inspired by [CS50](https://cs50.harvard.edu).

### ✨ Features

* 👨‍🏫 Instructor dashboard: create courses, upload videos, assign homework
* 👨‍🎓 Student view: browse courses, watch lecture videos, submit assignments
* ✅ Auto grading system with score tracking
* 📊 Performance dashboard with grades and history
* 🗄️ MySQL-based backend and Spring Boot REST API
* 🎨 Clean, responsive front-end in HTML/CSS/JS (CS50 style)

---

## 🏗️ Tech Stack

| Layer    | Technology                               |
| -------- | ---------------------------------------- |
| Backend  | Spring Boot, Spring MVC, Spring Data JPA |
| Database | MySQL 8.0                                |
| Frontend | HTML5, CSS3, Vanilla JS                  |
| Styling  | Custom CSS (CS50 theme)                  |
| Tools    | Maven, Git, VS Code / IntelliJ           |

---

## 📂 Project Structure

```
online-learning-platform/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/learningplatform/
│   │   │   ├── controllers/
│   │   │   ├── entities/
│   │   │   ├── repositories/
│   │   │   ├── services/
│   │   │   └── LearningPlatformApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/       # Thymeleaf or static HTML files
│
├── static/
│   ├── css/style.css
│   └── js/script.js
│
├── templates/
│   ├── login.html
│   ├── register.html
│   ├── index.html
│   ├── course.html
│   ├── assignment.html
│   └── grades.html
│
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/your-username/online-learning-platform.git
cd online-learning-platform
```

### 2. Configure MySQL

Create a database named `online_learning`. Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/online_learning
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the project

Use IntelliJ or run via Maven:

```bash
mvn spring-boot:run
```

Visit: [http://localhost:8080](http://localhost:8080)

---

## 📸 Screenshots

| Login Page                 | Course Dashboard             |
| -------------------------- | ---------------------------- |
| ![](screenshots/login.png) | ![](screenshots/courses.png) |

---

## 📄 License

This project is licensed under the MIT License. See `LICENSE` for more details.

---

## 🙋‍♂️ Author

Created by \[Your Name] — 22计算机科学与技术一班 / 学号 2210708130 / 任课教师 王晓霞
CSE / School Project Spring 2025

---
