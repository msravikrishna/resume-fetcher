package com.example.resume_fetcher.test;

import java.net.URI;
import java.net.http.*;

public class ClaudeKeyTester {
    public static void main(String[] args) throws Exception {
        //place a valid api key and run this test
        String apiKey = "";
        String prompt = "Say hello";

        HttpClient client = HttpClient.newHttpClient();
        String body = """
            {
              "model": "claude-3-sonnet-20240229",
              "max_tokens": 100,
              "messages": [{"role": "user", "content": "%s"}]
            }
            """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
