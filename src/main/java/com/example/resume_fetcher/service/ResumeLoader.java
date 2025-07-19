package com.example.resume_fetcher.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeLoader {

    private static final String RESUME_FOLDER = "./src/main/resources/resumes";

    public List<String> loadResumes() {
        List<String> resumes = new ArrayList<>();
        File folder = new File(RESUME_FOLDER);
        if (!folder.exists() || !folder.isDirectory()) return resumes;

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                try {
                    String content = Files.readString(file.toPath());
                    resumes.add(content);
                } catch (Exception e) {
                    System.out.println("Failed to read " + file.getName());
                }
            }
        }
        return resumes;
    }
}

