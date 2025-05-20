package com.netmarble.tossverification.client;

import com.netmarble.tossverification.auth.TossTokenHolder;
import com.netmarble.tossverification.config.TossAuthConstants;
import com.netmarble.tossverification.dto.external.toss.TossVerificationCheckApiInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TossVerificationClient {

    private final RestTemplate restTemplate;
    private final TossTokenHolder tokenHolder;

    public String checkStatus(String txId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenHolder.getValidToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("txId", txId);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TossVerificationCheckApiInDto> response = restTemplate.exchange(
                TossAuthConstants.TOSS_CERT_DEFAULT_URL + "/sign/user/auth/id/status",
                HttpMethod.POST,
                request,
                TossVerificationCheckApiInDto.class
        );

        TossVerificationCheckApiInDto responseDto = response.getBody();
        if(responseDto == null) {
            throw new RuntimeException("Empty response from Toss verification status check");
        }
        if(!"SUCCESS".equals(responseDto.getResultType()) || responseDto.getSuccess() == null) {
            throw new RuntimeException("Error checking Toss verification status: " +
                    (responseDto.getError() != null ? responseDto.getError().getReason() : "Unknown error"));
        }

        return responseDto.getSuccess().getStatus();
    }
}
