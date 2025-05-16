package com.netmarble.tossverification.auth;

import org.springframework.stereotype.Component;

@Component
public class TossTokenHolder {
    private static String accessToken;
    private static long expiresAt;

    public static synchronized void updateToken(String token, long expiresInSeconds) {
        TossTokenHolder.accessToken = token;
        TossTokenHolder.expiresAt = System.currentTimeMillis() + (expiresInSeconds * 1000);
    }

    public static synchronized String getAccessToken() {
        if (accessToken == null || System.currentTimeMillis() >= expiresAt) {
            return null;
        }
        return accessToken;
    }

    public static synchronized boolean isTokenExpired() {
        return accessToken == null || System.currentTimeMillis() >= expiresAt;
    }
}
