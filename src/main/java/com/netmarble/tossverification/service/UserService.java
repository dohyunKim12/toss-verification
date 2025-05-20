package com.netmarble.tossverification.service;

import com.netmarble.tossverification.dto.internal.request.CreateUserRequestDto;
import com.netmarble.tossverification.entity.NetmarbleIdentityVerification;
import com.netmarble.tossverification.repository.NetmarbleIdentityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final NetmarbleIdentityRepository netmarbleIdentityRepository;

    public String createUser(CreateUserRequestDto dto) {
        NetmarbleIdentityVerification netmarbleIdentityVerification = NetmarbleIdentityVerification.builder()
                .birthday(dto.getUserBirthday())
                .name(dto.getUsername())
                .phoneNumber(dto.getUserPhoneNumber())
                .description("test")
                .createDatetime(LocalDateTime.now())
                .updateDatetime(LocalDateTime.now())
                .build();
        netmarbleIdentityRepository.save(netmarbleIdentityVerification);

        return netmarbleIdentityVerification.getNetmarbleId().toString();
    }
}
