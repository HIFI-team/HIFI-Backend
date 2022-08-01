package Backend.HIFI.auth.jwt;

import Backend.HIFI.auth.dto.TokenResponseDto;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    private static final long JWT_EXPIRATION_MS = 1000 * 60 * 30; //30분
    private static final long REFRESH_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7; //7일
    private static final String AUTHORITIES_KEY = "role"; //권한 정보 컬럼명

    /** Jwt 토큰 생성
     * @param authentication 인증 요청하는 유저 정보
     */
    public TokenResponseDto generateToken(Authentication authentication)
            throws HttpServerErrorException.InternalServerError {
        //권한 가져오기
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
        final Date now = new Date();

        final Date accessTokenExpiresIn = new Date(now.getTime() + JWT_EXPIRATION_MS);
        final String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("hifi")
                .setIssuedAt(now) // 생성일자 지정(현재)
                .setSubject(authentication.getName()) // 사용자(principal, email)
                .claim(AUTHORITIES_KEY, authorities) //권한 설정
                .setExpiration(accessTokenExpiresIn) // 만료일자
                .signWith(SignatureAlgorithm.HS512, encodedKey) // signature에 들어갈 secret 값 세팅
                .compact();

        final Date refreshTokenExpiresIn = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_MS);
        final String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("hifi")
                .setExpiration(refreshTokenExpiresIn)
                .signWith(SignatureAlgorithm.HS512, encodedKey)
                .compact();

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
      final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
//            throw new RuntimeException("권한 정보가 없는 토큰입니다");
        }

        //권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        //Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
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
        } catch (SignatureException | MalformedJwtException ex) {
            log.error("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException ex) {
            log.error("만료된 JWT 토큰입ㄴ디ㅏ");
        } catch (UnsupportedJwtException ex) {
            log.error("지원하지 않는 JWT 토큰입니다");
        } catch (IllegalArgumentException ex) {
            log.error("JWT 토큰이 비어있습니다");
        }
        return false;
    }


    /**
     * JWT 토큰에서 claims 추출
     * @param accessToken AccessToken (JWT)
     * @return Claims
     */
    public Claims parseClaims(String accessToken) {
        final String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());

        try {
            return Jwts.parser()
                    .setSigningKey(encodedKey)
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
