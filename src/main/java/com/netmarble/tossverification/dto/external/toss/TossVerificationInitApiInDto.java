package com.netmarble.tossverification.dto.external.toss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationInitApiInDto {

    private String resultType;    // SUCCESS, FAIL
    private TossVerificationInitApiSuccessDto success;
    private TossApiErrorDto error;
}
