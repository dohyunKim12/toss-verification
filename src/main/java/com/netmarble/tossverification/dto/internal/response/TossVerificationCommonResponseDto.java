package com.netmarble.tossverification.dto.internal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationCommonResponseDto {
    private String resultType;
    private String txId;
    private String status;
    private LocalDateTime requestedAt;
    private String reason;
}
