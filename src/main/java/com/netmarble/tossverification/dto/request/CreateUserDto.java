package com.netmarble.tossverification.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String username;
    private String userPhoneNumber;
    private String userBirthday;
}
