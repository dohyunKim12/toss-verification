package com.netmarble.tossverification.controller;

import com.netmarble.tossverification.dto.internal.request.CreateUserRequestDto;
import com.netmarble.tossverification.dto.internal.request.TossVerificationAppPushRequestDto;
import com.netmarble.tossverification.dto.internal.response.TossVerificationCommonResponseDto;
import com.netmarble.tossverification.dto.internal.response.TossVerificationInitResponseDto;
import com.netmarble.tossverification.service.TossAuthService;
import com.netmarble.tossverification.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/TossAuth")
@RequiredArgsConstructor
@Tag(name = "Toss verification", description = "APIs for verify by Toss cert api")
public class TossAuthController {

    private final Logger logger = LoggerFactory.getLogger(TossAuthController.class);

    private final TossAuthService tossAuthService;

    private final UserService userService;

//    @Operation(summary = "Get all users info", description = "Retrieve all users information from DB")
//    @GetMapping
//    public ResponseEntity<List<UserResultDto>> getAllUsersInfo() {
//        List<UserResultDto> results = tossAuthService.getAllUsersInfo();
//        return ResponseEntity.ok(results);
//    }

    // User create
    // API to create user (For Test)
    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto dto) {
        String result = userService.createUser(dto);
        return ResponseEntity.ok(result);
    }

    // 1. Seq 기반으로 record 찾아서 반환 (netmarble-identity-verification 에서 verify_type toss, verify_seq 있으면 그 seq 넘겨받고 여기서는 레코드만 조회해서 반환)

    // 2. 본인확인 요청 (by app push)
    @PostMapping("/auth/request/app-push")
    public ResponseEntity<TossVerificationInitResponseDto> requestUserVerification(@RequestBody TossVerificationAppPushRequestDto requestDto) {
        TossVerificationInitResponseDto response = tossAuthService.requestVerification(requestDto);
        return ResponseEntity.ok(response);
    }

    // 2. 본인확인 상태 조회
    @PostMapping("/auth/request/status")
    public CompletableFuture<ResponseEntity<TossVerificationCommonResponseDto>> getVerificationStatus(@RequestParam("txId") String txId) {
        CompletableFuture<TossVerificationCommonResponseDto> future = tossAuthService.pollVerificationStatus(txId);
        return future.orTimeout(180, TimeUnit.SECONDS)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    logger.error("Polling failed", ex);
                    return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
                });
    }

    // 3. 결과 조회
    @GetMapping("/auth/result")
    public ResponseEntity<TossVerificationCommonResponseDto> getVerificationResult(@RequestParam("txId") String txId) {
        TossVerificationCommonResponseDto result = tossAuthService.getVerificationResult(txId);
        return ResponseEntity.ok(result);
    }
}
