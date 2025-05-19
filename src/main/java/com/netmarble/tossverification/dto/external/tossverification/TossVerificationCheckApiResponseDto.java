package com.netmarble.tossverification.dto.external.tossverification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationCheckApiResponseDto {

    private String resultType;    // SUCCESS, FAIL
    private TossVerificationCheckApiSuccessDto success;
    private TossVerificationApiErrorDto error;
}
