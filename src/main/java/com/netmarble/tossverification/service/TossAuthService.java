package com.netmarble.tossverification.service;

import com.netmarble.tossverification.auth.TossTokenHolder;
import com.netmarble.tossverification.client.TossVerificationClient;
import com.netmarble.tossverification.config.TossAuthConstants;
import com.netmarble.tossverification.dto.external.tossverification.TossVerificationApiResponseDto;
import com.netmarble.tossverification.dto.request.TossVerificationAppPushRequestDto;
import com.netmarble.tossverification.dto.response.TossVerificationCheckResponseDto;
import com.netmarble.tossverification.dto.response.TossVerificationResponseDto;
import com.netmarble.tossverification.entity.NetmarbleIdentityVerification;
import com.netmarble.tossverification.entity.TossVerificationInfo;
import com.netmarble.tossverification.repository.NetmarbleIdentityRepository;
import com.netmarble.tossverification.repository.TossAuthRepository;
import im.toss.cert.sdk.TossCertSession;
import im.toss.cert.sdk.TossCertSessionGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class TossAuthService {
    private final Logger logger = LoggerFactory.getLogger(TossAuthService.class);

    private final RestTemplate restTemplate;

    private final TossTokenHolder tossTokenHolder;

    private final TossVerificationClient tossVerificationClient;

    private final NetmarbleIdentityRepository netmarbleIdentityRepository;

    private final TossAuthRepository tossAuthRepository;

    // 2. 본인확인 요청 서비스 (by App Push)
    // 1) get Access Token
    // 2) request Toss verification (본인확인 요청 API case2, sync)
    // 3) update DB with txId
    public TossVerificationResponseDto requestVerification(TossVerificationAppPushRequestDto requestDto) {
        // Verify if the user exists in the DB
        NetmarbleIdentityVerification identityVerification = (NetmarbleIdentityVerification) netmarbleIdentityRepository.findByName(requestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + requestDto.getUsername()));

        // 1) get Access Token
        String accessToken = tossTokenHolder.getValidToken();

        // 2) request Toss verification (본인확인 요청 API case2, sync)
        // Create session
        TossCertSessionGenerator tossCertSessionGenerator = new TossCertSessionGenerator();
        TossCertSession tossCertSession = tossCertSessionGenerator.generate();
        String sessionKey = tossCertSession.getSessionKey();

        // Encrypt request data
        String encryptedUsername = tossCertSession.encrypt(requestDto.getUsername());
        String encryptedPhoneNumber = tossCertSession.encrypt(requestDto.getUserPhoneNumber());
        String encryptedUserBirthday = tossCertSession.encrypt(requestDto.getUserBirthday());

        // Call Toss API (case2)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("requestType", "USER_PERSONAL");
        requestBody.put("requestUrl", "http://localhost:8080");
        requestBody.put("triggerType", "PUSH");
        requestBody.put("userName", encryptedUsername);
        requestBody.put("userPhone", encryptedPhoneNumber);
        requestBody.put("userBirthday", encryptedUserBirthday);
        requestBody.put("sessionKey", sessionKey);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TossVerificationApiResponseDto> response = restTemplate.exchange(
                TossAuthConstants.TOSS_CERT_DEFAULT_URL + "/sign/user/auth/id/request",
                HttpMethod.POST,
                requestEntity,
                TossVerificationApiResponseDto.class
        );
        TossVerificationApiResponseDto responseDto = response.getBody();

        String txId = responseDto.getSuccess().getTxId();
        LocalDateTime requestedAt = OffsetDateTime.parse(responseDto.getSuccess().getRequestedDt()).toLocalDateTime();
        LocalDateTime currentTime = LocalDateTime.now();

        if(responseDto.getResultType().equals("SUCCESS")) {
            // If success,
            // 3) create DB record with txId
            TossVerificationInfo entity = new TossVerificationInfo();
            entity.setTxId(responseDto.getSuccess().getTxId());
            entity.setStatus("REQUESTED");
            entity.setCreatedAt(currentTime);
            entity.setUpdatedAt(currentTime);
            entity.setRequestedAt(requestedAt);
            TossVerificationInfo savedEntity = tossAuthRepository.save(entity);

            identityVerification.setVerifyType("TOSS");
            identityVerification.setVerifySeq(savedEntity.getTossVerifySeq());
            netmarbleIdentityRepository.save(identityVerification);

            return new TossVerificationResponseDto(txId, null, null, null, null, requestedAt);
        } else {
            // If failed,
            throw new RuntimeException("Toss verification request failed: " + responseDto.getError().getReason());
        }
    }

    @Async("tossAuthCheckPollingExecutor")
    public CompletableFuture<TossVerificationCheckResponseDto> pollVerificationStatus(String txId) {
        return CompletableFuture.supplyAsync(() -> {
            int maxAttempts = 100;
            int delayMs = 1000;

            for (int i = 0; i < maxAttempts; i++) {
                try {
                    String status = tossVerificationClient.checkStatus(txId);
                    if ("COMPLETED".equals(status)) {
                        // Return to front-end
                        LocalDateTime requestedAt = tossAuthRepository.findByTxId(txId)
                                .orElseThrow(() -> new RuntimeException("Transaction not found with txId: " + txId))
                                .getRequestedAt();
                        return new TossVerificationCheckResponseDto(txId, "COMPLETED", requestedAt, null);
                    }
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    logger.error("Polling failed for txId: {}", txId, e);
                    throw new RuntimeException(e);
                }
            }
            logger.error("Polling timed out for txId: {}", txId);
            throw new RuntimeException("Polling timed out for txId: " + txId);
        });

        // 3. 본인확인 상태조회 서비스 (프론트에서 인증완료 버튼 누르게끔 할 게 아니면, 비동기 polling)
        // 1) get Access Token
        // 2) request Toss verification (본인확인 상태조회)
        // 3) update DB with status

        // 4. 본인확인 결과조회 서비스
        // 1) get Access Token
        // 2) request Toss verification (본인확인 결과조회)
        // 3) update DB with status, ci, di, signature
    }
}
