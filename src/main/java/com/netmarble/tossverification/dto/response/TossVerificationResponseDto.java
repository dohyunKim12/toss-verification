package com.netmarble.tossverification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationResponseDto {
    private String txId;
    private String authUrl;
    private String appScheme;
    private String androidAppUri;
    private String iosAppUri;
    private LocalDateTime requestedAt;
}
