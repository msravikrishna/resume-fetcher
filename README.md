SpringBoot Application
Claude 

# 🔍 Resume Matcher using Spring Boot + Claude AI

This project is a **Spring Boot application** that utilizes **Claude AI** to intelligently match job descriptions with resumes stored on your local machine. It automates the task of finding the most relevant resume based on the job posting by generating prompts from the job description and evaluating the content of available resumes.

---

## 📌 Features

- ✅ Spring Boot REST API
- 🤖 Uses Claude (AI) for semantic matching
- 📂 Searches local resume files (.txt format)
- 🔍 Returns the best-matched resume/candidates
- 📝 Logs and displays 

---

## 🚀 How It Works

1. User sends a job description (JD) via an API endpoint.
2. The application uses Claude AI to generate a tailored prompt based on the JD.
3. It reads and parses resumes from a specified local directory.
4. Claude evaluates and scores resumes against the JD prompt.
5. The best-matching resume is returned as the result.

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Claude API (via HTTP calls)

---

## 📂 Future Work
Support PDF and Docx type resumes

