package com.dabuliu.blog.user;

import java.time.Duration;
import java.time.Instant;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


//根据用户名生成 Token
@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(String username) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("personal-blog")
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofHours(2)))
                .subject(username)
                .build();

        // issuer：谁签发（personal-blog）
        // issuedAt：现在签发
        // expiresAt：两小时后失效
        // subject：这份Token属于哪个用户名

        JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(
                        header,
                        claims))
                .getTokenValue();
    }
}