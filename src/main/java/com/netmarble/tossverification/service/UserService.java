package com.netmarble.tossverification.service;

import com.netmarble.tossverification.auth.TossTokenHolder;
import com.netmarble.tossverification.config.TossAuthConstants;
import com.netmarble.tossverification.dto.external.tossverification.TossVerificationApiResponseDto;
import com.netmarble.tossverification.dto.request.CreateUserDto;
import com.netmarble.tossverification.dto.request.TossVerificationAppPushRequestDto;
import com.netmarble.tossverification.dto.response.TossVerificationResponseDto;
import com.netmarble.tossverification.entity.NetmarbleIdentityVerification;
import com.netmarble.tossverification.entity.TossVerificationInfo;
import com.netmarble.tossverification.repository.NetmarbleIdentityRepository;
import com.netmarble.tossverification.repository.TossAuthRepository;
import im.toss.cert.sdk.TossCertSession;
import im.toss.cert.sdk.TossCertSessionGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final NetmarbleIdentityRepository netmarbleIdentityRepository;

    public String createUser(CreateUserDto dto) {
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
