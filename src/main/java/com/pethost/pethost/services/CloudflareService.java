package com.pethost.pethost.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CloudflareService {

    @Value("${cloudflare.api.token}")
    private String cloudflareToken;

    @Value("${cloudflare.api.global-key}")
    private String globalKey;

    @Value("${cloudflare.api.origin-key}")
    private String originKey;

    private final RestTemplate restTemplate;

    public CloudflareService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> verifyToken() {
        String url = "https://api.cloudflare.com/client/v4/accounts/e2513d2df84f6326d07319b84464bb7b/tokens/verify";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cloudflareToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
