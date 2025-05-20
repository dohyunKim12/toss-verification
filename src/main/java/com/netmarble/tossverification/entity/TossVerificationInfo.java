package com.netmarble.tossverification.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_toss_verification_info")
public class TossVerificationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toss_verify_seq")
    private Long tossVerifySeq;

    @Column(name = "tx_id", length = 100, nullable = false)
    private String txId;

    @Column(name = "ci", length = 150)
    private String ci;

    @Column(name = "di", length = 150)
    private String di;

    @Column(name = "status", length = 30) // REQUESTED, IN_PROGRESS, COMPLETED(본인확인 결과조회 응답 기준 Set), EXPIRED
    private String status;

    @Column(name = "signature", columnDefinition = "TEXT")
    private String signature;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
