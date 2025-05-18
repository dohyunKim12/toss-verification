package com.netmarble.tossverification.service;

import com.netmarble.tossverification.repository.TossAuthRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TossAuthService {
    private final Logger logger = LoggerFactory.getLogger(TossAuthService.class);

    private final TossAuthRepository tossAuthRepository;

    // 2. 본인확인 요청 서비스 (by App Push)
    // 1) get Access Token
    // 2) request Toss verification (본인확인 요청 API case2, sync)
    // 3) update DB with txId

    // 3. 본인확인 상태조회 서비스 (프론트에서 인증완료 버튼 누르게끔 할 게 아니면, 비동기 polling)
    // 1) get Access Token
    // 2) request Toss verification (본인확인 상태조회)
    // 3) update DB with status

    // 4. 본인확인 결과조회 서비스
    // 1) get Access Token
    // 2) request Toss verification (본인확인 결과조회)
    // 3) update DB with status, ci, di, signature

}
