package com.netmarble.tossverification.auth;

import com.netmarble.tossverification.config.TossAuthConstants;
import com.netmarble.tossverification.dto.external.toss.TossTokenInDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Component
public class TossTokenHolder {

    private final RestTemplate restTemplate;
    private final String clientId = "test_a8e23336d673ca70922b485fe806eb2d"; // Todo : move to properties
    private final String clientSecret = "test_418087247d66da09fda1964dc4734e453c7cf66a7a9e3";
    private String accessToken; // Todo : DB에 저장 필요?
    private Instant expiresAt;

    public TossTokenHolder(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public synchronized String getValidToken() {
        if (accessToken == null || isExpired()) {
            refreshToken();
        }
        return accessToken;
    }

    private boolean isExpired() {
        return expiresAt == null || Instant.now().isAfter(expiresAt.minusSeconds(30));
    }

    private void refreshToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String body = "grant_type=client_credentials" +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&scope=ca";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<TossTokenInDto> response = restTemplate.exchange(
                TossAuthConstants.TOSS_TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                TossTokenInDto.class
        );

        TossTokenInDto tokenResponse = response.getBody();
        if (tokenResponse != null) {
            this.accessToken = tokenResponse.getAccessToken();
            this.expiresAt = Instant.now().plusSeconds(tokenResponse.getExpiresIn());
        } else {
            throw new RuntimeException("Toss token response is null");
        }
    }
}

