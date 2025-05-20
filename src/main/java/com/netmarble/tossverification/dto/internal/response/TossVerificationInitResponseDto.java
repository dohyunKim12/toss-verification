package com.netmarble.tossverification.dto.internal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationInitResponseDto {
    private String txId;
    private String authUrl;
    private String appScheme;
    private String androidAppUri;
    private String iosAppUri;
    private LocalDateTime requestedAt;
}
