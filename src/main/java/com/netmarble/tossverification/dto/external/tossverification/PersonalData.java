package com.netmarble.tossverification.dto.external.tossverification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {

    private String ci;
    private String name;
    private String birthday;
    private String gender;
    private String nationality;
    private String di;
}
