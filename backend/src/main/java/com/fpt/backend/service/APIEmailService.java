package com.fpt.backend.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIEmailService {
    private static final String URL =
      "https://api.mailboxvalidator.com/v2/validation/single?email={email}&key={key}&format=json";

    private final RestTemplate restTemplate;
    private final String apiKey = "0FSY0O3707DJM8BG30EQ"; 

    public APIEmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isEmailVerified(String email) {
        Map resp = restTemplate.getForObject(URL, Map.class, email, apiKey);
        return resp != null && Boolean.TRUE.equals(resp.get("is_verified"));
    }

}
