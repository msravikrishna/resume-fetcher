package com.example.resume_fetcher.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClaudeService {

    private final ResumeLoader resumeLoader;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${claude.api.key}")
    private String apiKey;

    @Value("${rank.resume.mock}")
    private Boolean isMockEnabled;

    public ClaudeService(ResumeLoader resumeLoader) {
        this.resumeLoader = resumeLoader;
    }

    public String rankCandidates(String jobDescription) {
        try {
            List<String> resumes = resumeLoader.loadResumes();

            StringBuilder prompt = new StringBuilder();
            prompt.append("You are an AI recruiter.\n");
            prompt.append("Job Description:\n").append(jobDescription).append("\n\n");
            prompt.append("Candidates:\n");
            for (int i = 0; i < resumes.size(); i++) {
                prompt.append("Candidate ").append(i + 1).append(":\n").append(resumes.get(i)).append("\n\n");
            }
            prompt.append("Which 3 candidates best fit the job? Explain your choice.");


            if (isMockEnabled) {
                //provide sample response
                return "Using mock response... No resumes match with the provided job description";
            }
            // Build API request
            HttpClient client = HttpClient.newHttpClient();
            Map<String, Object> payload = getPayload(prompt);
            HttpRequest request = buildRequest(payload);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Extract Claude's reply
                Map result = mapper.readValue(response.body(), Map.class);

                var content = ((List<Map<String, Object>>) result.get("content")).get(0).get("text");

                return content.toString();
            } else {
                return response.body();
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static Map<String, Object> getPayload(StringBuilder prompt) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "claude-3-sonnet-20240229");
        payload.put("max_tokens", 1024);
        payload.put("messages", List.of(Map.of("role", "user", "content", prompt.toString())));
        return payload;
    }

    private HttpRequest buildRequest(Map<String, Object> payload) throws JsonProcessingException {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payload)))
                .build();
    }
}
