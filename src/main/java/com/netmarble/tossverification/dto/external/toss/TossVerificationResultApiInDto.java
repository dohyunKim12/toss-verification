package com.netmarble.tossverification.dto.external.toss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationResultApiInDto {

    private String resultType;    // SUCCESS, FAIL
    private TossVerificationResultApiSuccessDto success;
    private TossApiErrorDto error;
}
