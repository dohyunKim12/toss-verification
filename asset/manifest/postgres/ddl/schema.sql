CREATE TABLE tb_netmarble_identity_verification (
                                                    netmarble_id        String PRIMARY KEY,
                                                    verify_type         VARCHAR(20) NOT NULL,
                                                    verify_seq          BIGINT NOT NULL,
                                                    level               INTEGER,
                                                    birthday            DATE,
                                                    privacy_agreement   BOOLEAN DEFAULT FALSE,
                                                    name                VARCHAR(100),
                                                    phone_number        VARCHAR(20),
                                                    manager             VARCHAR(100),
                                                    description         TEXT,
                                                    create_datetime     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                                    update_datetime     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tb_toss_verification_info (
                                           toss_verify_seq     BIGSERIAL PRIMARY KEY,
                                           tx_id               VARCHAR(100) NOT NULL UNIQUE,
                                           ci                  TEXT NOT NULL,
                                           di                  TEXT NOT NULL,
                                           status              VARCHAR(20) NOT NULL,
                                           signature           TEXT NOT NULL,
                                           requested_at        TIMESTAMP WITH TIME ZONE NOT NULL,
                                           completed_at        TIMESTAMP WITH TIME ZONE,
                                           created_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                           updated_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
