package com.netmarble.tossverification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_netmarble_identity_verification")
public class NetmarbleIdentityVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID netmarbleId;

    @Column(name = "verify_type", length = 20) // "KMC", "TOSS"
    private String verifyType;

    @Column(name = "verify_seq")
    private Long verifySeq;

    @Column(name = "level")
    private Integer level = 0;

    @Column(name = "birthday", length = 8)
    private String birthday;

    @Column(name = "privacy_agreement")
    private Boolean privacyAgreement = false;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "manager", length = 50)
    private String manager;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "create_datetime")
    private LocalDateTime createDatetime;

    @Column(name = "update_datetime")
    private LocalDateTime updateDatetime;
}
