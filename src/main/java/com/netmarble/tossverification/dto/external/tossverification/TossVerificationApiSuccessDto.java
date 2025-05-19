package com.netmarble.tossverification.dto.external.tossverification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationApiSuccessDto {

    private String txId;
    private String authUrl; // For case 1
    private String appScheme; // For case 3
    private String androidAppUri; // For case 3
    private String iosAppUri; // For case 3
    private String requestedDt;
}
