package com.example.util;

import com.example.dto.auth.JwtDTO;
import com.example.enums.AdminRole;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String secretKey = "mazgiTopSecret123!";
    private static final int tokenLiveTime = 1000 * 3600 * 24 * 7;

    public static String encode(String username, AdminRole role){
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512,secretKey);

        jwtBuilder.claim("username", username);

        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("DoniyorShifo klinika");
        return jwtBuilder.compact();
    }

    public static JwtDTO decodeToken(String token) {

        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        String username = (String) claims.get("username");

        String role = (String) claims.get("role");
        AdminRole profileRole = AdminRole.valueOf(role);

        return new JwtDTO(username, profileRole);

    }
}
