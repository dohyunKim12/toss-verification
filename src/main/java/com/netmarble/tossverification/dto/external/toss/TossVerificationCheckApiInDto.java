package com.netmarble.tossverification.dto.external.toss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationCheckApiInDto {

    private String resultType;    // SUCCESS, FAIL
    private TossVerificationCheckApiSuccessDto success;
    private TossApiErrorDto error;
}
