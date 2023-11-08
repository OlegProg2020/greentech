package org.greentech.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.entity.Account;
import org.greentech.backend.service.security.AccountSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {

    private final AccountSecurityManager accountSecurityManager;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    public String createToken(String phone) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000L);

        return JWT.create()
                .withIssuer(phone)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(this.secretKey));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.secretKey)).build();

        DecodedJWT decoded = verifier.verify(token);

        Account account = accountSecurityManager.findByPhone(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(
                account,
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getRole().toString())));
    }
}
