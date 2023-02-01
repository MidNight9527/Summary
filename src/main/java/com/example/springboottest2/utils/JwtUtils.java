package com.example.springboottest2.utils;

import cn.hutool.crypto.asymmetric.RSA;
import com.example.springboottest2.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static RSA rsa = new RSA(RSAUtils.getKeyFromFile("key.pri"), RSAUtils.getKeyFromFile("key.pub"));
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static String createJwtToken(User user) {
        Map claims = new HashMap<>();
        claims.put("userId", user.getUserid());
        claims.put("userName", user.getUsername());
        claims.put("password", user.getPassword());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + 2 * 60 * 60 * 1000))
                .setSubject(user.getUserid().toString())
                .signWith(rsa.getPrivateKey())
                .compact();
    }

    public static Claims resolveToken(String token) throws Exception {
        Jws<Claims> jws;
        jws = Jwts.parserBuilder()  // (1)
                .setSigningKey(rsa.getPublicKey())         // (2)
                .build()                    // (3)
                .parseClaimsJws(token); // (4)
        return jws.getBody();
        // we can safely trust the JWT
    }

    public static User getTokenInfo(String token) {
        try {
            Claims claims = resolveToken(token);
            User user = new User();
            user.setUserid((Integer) claims.get("userId"));
            user.setUsername((String) claims.get("userName"));
            user.setPassword((String) claims.get("password"));
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
