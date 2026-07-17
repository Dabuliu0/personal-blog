package com.dabuliu.blog.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import com.dabuliu.blog.config.JwtConfig;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.security.oauth2.jwt.JwtException;

class JwtServiceTest {

    @Test
    void shouldGenerateTokenForUsername() {
        JwtConfig config = new JwtConfig();

        String testSecret = "test-secret-key-must-be-at-least-32-bytes";

        SecretKey secretKey = config
                .jwtSecretKey(testSecret);

        JwtService service = new JwtService(
                config.jwtEncoder(secretKey));

        JwtDecoder decoder = config
                .jwtDecoder(secretKey);

        String token = service
                .generateToken("testuser");

        Jwt decodedToken = decoder.decode(token);

        assertEquals(
                "testuser",
                decodedToken.getSubject());

        assertTrue(
                decodedToken.getExpiresAt()
                        .isAfter(Instant.now()));
    }

    @Test
    void shouldRejectTamperedToken() {
        JwtConfig config = new JwtConfig();

        String testSecret = "test-secret-key-must-be-at-least-32-bytes";

        SecretKey secretKey = config
                .jwtSecretKey(testSecret);

        JwtService service = new JwtService(
                config.jwtEncoder(secretKey));

        JwtDecoder decoder = config
                .jwtDecoder(secretKey);

        String token = service
                .generateToken("testuser");

        assertThrows(
                JwtException.class,
                () -> decoder.decode(token + "x"));
    }
}