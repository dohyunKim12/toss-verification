package com.netmarble.tossverification.dto.external.toss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationResultApiSuccessDto {

    private String txId;
    private String status;
    private String signature;
    private String completedDt;
    private String requestedDt;
    private PersonalData personalData;
}
