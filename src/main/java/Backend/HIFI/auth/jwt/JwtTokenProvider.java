package Backend.HIFI.auth.jwt;

import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRole;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/** JWT 토큰 생성에 관여하는 클래스입니다
 * @author gengminy (220728) */
@Slf4j
@Component
public class JwtTokenProvider {
    /** 토큰 비밀 키 */
    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET;

    /** 토큰 유효 시간 (ms) */
    private static final int JWT_EXPIRATION_MS = 604800000;

    /** Jwt 토큰 생성
     * @param authentication 인증 요청하는 유저 정보
     */
    public String generateToken(Authentication authentication)
            throws HttpServerErrorException.InternalServerError {
        final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());

        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("hifi")
                .setSubject((String) authentication.getPrincipal()) // 사용자(principal)
                .setIssuedAt(new Date()) // 생성일자 지정(현재)
                .setExpiration(expiryDate) // 만료일자
                .claim("email", authentication.getPrincipal())
                .claim("role", authentication.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, encodedKey) // signature에 들어갈 secret 값 세팅
                .compact();
    }

    /**
     * JWT 토큰에서 이메일 추출
     * @param token AccessToken (JWT)
     * @return email address (String)
     */
    public String getUserEmailFromJWT(String token) {
        final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
        Claims claims = Jwts.parser()
                .setSigningKey(encodedKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Authentication getAuthenticationFromJWT(String token) {
      final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
        Claims claims = Jwts.parser()
                .setSigningKey(encodedKey)
                .parseClaimsJws(token)
                .getBody();


        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    /**
     * JWT 유효성 검사
     * @param token 검사 대상 JWT
     * @returns boolean
     * @throws SignatureException 서명이 다를때
     * @throws MalformedJwtException JWT 구조가 아닐때
     * @throws ExpiredJwtException 만료기간이 지났을때
     * @throws UnsupportedJwtException 지원 불가
     * @throws IllegalArgumentException 매개변수 전달 오류
     */
    public boolean validateToken(String token) {
        final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
        try {
            Jwts.parser().setSigningKey(encodedKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
        }
        return false;
    }


}
