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
        logger.info("Creating user with username: {}", dto.getUsername());
        // Check if the user already exists
        if (netmarbleIdentityRepository.existsByName(dto.getUsername())) {
            logger.error("User with username {} already exists", dto.getUsername());
            throw new IllegalArgumentException("User already exists");
        }

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
