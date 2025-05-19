package com.netmarble.tossverification.dto.external.tossverification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationApiResponseDto {

    private String resultType;    // SUCCESS, FAIL
    private TossVerificationApiSuccessDto success;
    private TossVerificationApiErrorDto error;
}
