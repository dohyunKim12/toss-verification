package com.netmarble.tossverification.dto.external.toss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossApiErrorDto {

    private int errorType;
    private String errorCode;
    private String reason;
    private Object data;
    private String title;
}
