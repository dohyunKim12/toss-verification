package com.netmarble.tossverification.controller;

import com.netmarble.tossverification.dto.request.CreateUserDto;
import com.netmarble.tossverification.dto.request.TossVerificationAppPushRequestDto;
import com.netmarble.tossverification.dto.response.TossVerificationResponseDto;
import com.netmarble.tossverification.service.TossAuthService;
import com.netmarble.tossverification.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/TossAuth")
@RequiredArgsConstructor
@Tag(name = "Toss verification", description = "APIs for verify by Toss cert api")
public class TossAuthController {

    private final TossAuthService tossAuthService;

    private final UserService userService;

//    @Operation(summary = "Get all users info", description = "Retrieve all users information from DB")
//    @GetMapping
//    public ResponseEntity<List<UserResultDto>> getAllUsersInfo() {
//        List<UserResultDto> results = tossAuthService.getAllUsersInfo();
//        return ResponseEntity.ok(results);
//    }

//    // 1. API to get Access Token (For Test)
//    @Operation(summary = "Get Access Token(For Test)", description = "Retrieve access token using authorization code")
//    @PostMapping("/token/refresh")
//    public String refreshAccessToken() {
//        return tossAuthService.issueAccessToken();
//    }
    // User create
    // 1. API to create user (For Test)
    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto dto) {
        String result = userService.createUser(dto);
        return ResponseEntity.ok(result);
    }

    // 1. Seq 기반으로 record 찾아서 반환 (netmarble-identity-verification 에서 verify_type toss, verify_seq 있으면 그 seq 넘겨받고 여기서는 레코드만 조회해서 반환)

    // 2. 본인확인 요청 (by app push)
    @PostMapping("/auth/request/app-push")
    public ResponseEntity<TossVerificationResponseDto> requestUserVerification(@RequestBody TossVerificationAppPushRequestDto requestDto) {
        TossVerificationResponseDto response = tossAuthService.requestVerification(requestDto);
        return ResponseEntity.ok(response);
    }

//    // 3. 결과 조회
//    @GetMapping("/auth/result")
//    public TossAuthResultDto getVerificationResult(@RequestParam("txId") String txId) {
//        return tossAuthService.fetchVerificationResult(txId);
//    }
}
