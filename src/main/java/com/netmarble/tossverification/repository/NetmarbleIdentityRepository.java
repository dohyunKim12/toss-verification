package com.netmarble.tossverification.repository;

import com.netmarble.tossverification.entity.NetmarbleIdentityVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NetmarbleIdentityRepository extends JpaRepository<NetmarbleIdentityVerification, Long> {
    Optional<Object> findByName(String name);
}
