package com.netmarble.tossverification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TossAuth")
@Tag(name = "Toss verification", description = "APIs for verify by Toss cert api")
public class TossAuthController {

    private final TossAuthService tossAuthService;

    public TossAuthController(TossAuthService tossAuthService) {
        this.tossAuthService = tossAuthService;
    }

    @Operation(summary = "Get all users info", description = "Retrieve all users information from DB")
    @GetMapping
    public ResponseEntity<List<UserResultDto>> getAllUsersInfo() {
        List<UserResultDto> results = tossAuthService.getAllUsersInfo();
        return ResponseEntity.ok(results);
    }

    // 1. API to get Access Token (For Test)
    @Operation(summary = "Get Access Token(For Test)", description = "Retrieve access token using authorization code")
    @PostMapping("/token/refresh")
    public String refreshAccessToken() {
        return tossAuthService.issueAccessToken();
    }

    // 2. 본인확인 요청
    @PostMapping("/auth/request")
    public String requestUserVerification(@RequestBody TossAuthRequestDto requestDto) {
        return tossAuthService.requestVerification(requestDto);
    }

    // 3. 결과 조회
    @GetMapping("/auth/result")
    public TossAuthResultDto getVerificationResult(@RequestParam("txId") String txId) {
        return tossAuthService.fetchVerificationResult(txId);
    }
}
