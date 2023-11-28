package com.example.future.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "bban";
    private static String signKey_2 = "bbban";
    //防沉迷模式 doge
    private static Long expire = 7200000L;//1h
    public static String generateJwt(Map<String,Object> claims,int jud){
       String jwt;
        if(jud == 1){
           jwt = Jwts.builder()
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS256,signKey)
                    .setExpiration(new Date(System.currentTimeMillis() + expire))
                    .compact();

        }
       else {
            jwt = Jwts.builder()
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS256,signKey_2)
                    .setExpiration(new Date(System.currentTimeMillis() + expire))
                    .compact();
        }
        return jwt;
    }

    public static Claims parseJWT(String jwt,int jud){
        if(jud == 1){
            return Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        }
        return Jwts.parser()
                .setSigningKey(signKey_2)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
