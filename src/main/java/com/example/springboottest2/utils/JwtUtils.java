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
//    private static RSA rsa = new RSA(RSAUtils.getKeyFromFile("key.pri"), RSAUtils.getKeyFromFile("key.pub"));
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    public static String createJwtToken(User user) {
//        Map claims = new HashMap<>();
//        claims.put("ID", user.getUserid());
//        claims.put("name", user.getUsername());
//        claims.put("age", user.getUserage());
//        claims.put("phone", user.getUserphone());
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(new Date().getTime() + 2 * 60 * 60 * 1000))
//                .setSubject(user.getUserid().toString())
//                .signWith(rsa.getPrivateKey())
//                .compact();
//    }
//
//    public static Claims resolveToken(String token) throws Exception {
//        Jws<Claims> jws;
//        jws = Jwts.parserBuilder()  // (1)
//                .setSigningKey(rsa.getPublicKey())         // (2)
//                .build()                    // (3)
//                .parseClaimsJws(token); // (4)
//        return jws.getBody();
//        // we can safely trust the JWT
//    }
//
//    public static User getTokenInfo(String token) {
//        try {
//            Claims claims = resolveToken(token);
//            User user = new User();
//            user.setUserid((Integer) claims.get("ID"));
//            user.setUsername((String) claims.get("name"));
//            user.setUserage((String) claims.get("age"));
//            user.setUserphone((String) claims.get("phone"));
//            return user;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
