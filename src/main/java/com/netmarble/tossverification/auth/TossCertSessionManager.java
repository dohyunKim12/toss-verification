package com.netmarble.tossverification.auth;

import im.toss.cert.sdk.TossCertSession;
import im.toss.cert.sdk.TossCertSessionGenerator;
import org.springframework.stereotype.Component;

@Component
public class TossCertSessionManager {

    private final TossCertSessionGenerator generator = new TossCertSessionGenerator();

    public TossCertSession createSession() {
        return generator.generate();
    }
}
