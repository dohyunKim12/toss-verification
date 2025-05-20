package com.netmarble.tossverification.dto.internal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    private String username;
    private String userPhoneNumber;
    private String userBirthday;
}
