package com.netmarble.tossverification.repository;

import com.netmarble.tossverification.entity.TossVerificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TossAuthRepository extends JpaRepository<TossVerificationInfo, Long> {

    Optional<TossVerificationInfo> findByTxId(String txId);
}
