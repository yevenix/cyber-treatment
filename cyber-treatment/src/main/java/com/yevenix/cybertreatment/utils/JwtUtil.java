package com.yevenix.cybertreatment.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;  // 签名密钥：用于加密和解密token
    @Value("${jwt.expire}")
    private long expire;    // token的过期时间

    /**
     * 获取签名密钥
     * @return
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成token
     * @param userId
     * @param role
     * @return token
     */
    public String generateToken(Long userId, String role) {
        return Jwts.builder()
                .subject(String.valueOf(userId))    // 设置用户ID
                .claim("role", role)    // 设置角色
                .issuedAt(new Date())   // 设置签发时间
                .expiration(new Date(System.currentTimeMillis() + expire))  // 设置过期时间
                .signWith(getSigningKey())   // 设置签名密钥
                .compact(); // 生成token
    }

    /**
     * 解析token
     * @param token
     * @return Claims对象
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())   // 验证签名密钥
                .build()    // 构建解析器
                .parseSignedClaims(token)   // 解析token
                .getPayload();  // 获取payload
    }

    /**
     * 获取用户ID
     * @param token
     * @return 用户ID
     */
    public Long getUserId(String token) {
        Claims claims = parseToken(token);
        return Long.parseLong(claims.getSubject());
    }

    /**
     * 获取角色
     * @param token
     * @return 角色
     */
    public String getRole(String token) {
        Claims claims = parseToken(token);
        return claims.get("role", String.class);
    }

    /**
     * 验证token
     * @param token
     * @return true/false
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            // 判断token是否过期
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            // 解析失败
            return false;
        }
    }
}
