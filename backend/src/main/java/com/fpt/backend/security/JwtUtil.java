package com.fpt.backend.security;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.fpt.backend.util.Constant;
import io.jsonwebtoken.*;

@Component
public class JwtUtil {

    public String generateToken(int userId, int roleId, String typeJWT) {
        try {
            return Jwts.builder()
                    .setSubject(String.valueOf(userId))
                    .claim("roleId", roleId)
                    .claim("typeJWT", typeJWT)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()
                            + (typeJWT.equals(Constant.TYPEJWT_LOGIN) ? Constant.EXPIRATION_TIME : Constant.EXPIRATION_TIME_RESETPASSWORD)))
                    .signWith(Constant.SIGNING_KEY, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tạo JWT: " + e.getMessage());
            return null;
        }
    }

    public String extractUserId(String token) {
        try {
            return parseClaims(token).getSubject();
        } catch (Exception e) {
            System.err.println("❌ extractUserId lỗi: " + e.getMessage());
            return null;
        }
    }

    public int extractRoleId(String token) {
        try {
            return parseClaims(token).get("roleId", Integer.class);
        } catch (Exception e) {
            System.err.println("❌ extractRoleId lỗi: " + e.getMessage());
            return -1;
        }
    }

    public String extractTypeJWT(String token) {
        try {
            return parseClaims(token).get("typeJWT", String.class);
        } catch (Exception e) {
            System.err.println("❌ extractTypeJWT lỗi: " + e.getMessage());
            return null;
        }
    }

    public boolean validateToken(String token, int userId, String expectedTypeJWT) {
        try {
            Claims claims = parseClaims(token);

            String extractedUserId = claims.getSubject();
            String extractedTypeJWT = claims.get("typeJWT", String.class);

            return extractedUserId != null &&
                    extractedUserId.equals(String.valueOf(userId)) &&
                    extractedTypeJWT != null &&
                    extractedTypeJWT.equals(expectedTypeJWT) &&
                    !isTokenExpired(claims);

        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("❌ Token không hợp lệ: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Constant.SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
