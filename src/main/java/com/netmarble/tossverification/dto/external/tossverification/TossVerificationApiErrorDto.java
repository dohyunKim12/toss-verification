package com.netmarble.tossverification.dto.external.tossverification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossVerificationApiErrorDto {

    private int errorType;
    private String errorCode;
    private String reason;
    private Object data;
    private String title;
}
