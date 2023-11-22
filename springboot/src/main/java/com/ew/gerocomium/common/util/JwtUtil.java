package com.ew.gerocomium.common.util;

import com.ew.gerocomium.common.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {
    /**
     * 签发token
     *
     * @param id 用户id
     * @return jwt
     */
    public static String createJwt(String id) {
        Date signTime = new Date();
        return Jwts.builder()
                // 头部
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                // 主题
                .setSubject(Constant.TOKEN_SUBJECT)
                // 发行人
                .setIssuer(Constant.TOKEN_ISSURE)
                // 签发时间
                .setIssuedAt(signTime)
                // 生效时间
                .setNotBefore(signTime)
                // 到期时间
                .setExpiration(new Date(signTime.getTime() + Constant.EXPIRE_TIME))
                // 登录用户id
                .claim("id", id)
                // 签名哈希
                .signWith(Keys.hmacShaKeyFor(Constant.TOKEN_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                // 签名
                .compact();
    }

    /**
     * 解析token
     */
    public static Jws<Claims> parseJwt(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Constant.TOKEN_SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(jwt);
    }
}
