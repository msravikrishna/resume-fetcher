package com.example.resume_fetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ResumeFetcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeFetcherApplication.class, args);
	}

}

