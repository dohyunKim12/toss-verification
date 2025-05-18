package com.netmarble.tossverification.repository;

import com.netmarble.tossverification.entity.TossVerificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TossAuthRepository extends JpaRepository<TossVerificationInfo, Long> {

    TossVerificationInfo findByTxId(String txId);
}
