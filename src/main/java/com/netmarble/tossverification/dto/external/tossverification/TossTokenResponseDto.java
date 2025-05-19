package com.netmarble.tossverification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TossTokenResponseDto {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    private String scope;

    @JsonProperty("token_type")
    private String tokenType;
}
