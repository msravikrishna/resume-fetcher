package com.example.resume_fetcher.controller;

import com.example.resume_fetcher.service.ClaudeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecruiterController {

    private final ClaudeService claudeService;

    public RecruiterController(ClaudeService claudeService) {
        this.claudeService = claudeService;
    }

    @PostMapping("/rank-candidates")
    public String rankCandidates(@RequestBody String jobDescription) {
        return claudeService.rankCandidates(jobDescription);
    }
}
