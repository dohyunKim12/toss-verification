package com.netmarble.tossverification.dto.external.tossverification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationResultApiResponseDto {

    private String resultType;    // SUCCESS, FAIL
    private TossVerificationResultApiSuccessDto success;
    private TossVerificationApiErrorDto error;
}
