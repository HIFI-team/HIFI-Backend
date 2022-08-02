package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.TokenRequestDto;
import Backend.HIFI.auth.dto.TokenResponseDto;
import Backend.HIFI.auth.dto.UserRequestDto;
import Backend.HIFI.auth.dto.UserResponseDto;
import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.security.UserAuthentication;
import Backend.HIFI.common.exception.BadRequestException;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import Backend.HIFI.user.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional
    public UserResponseDto join(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new RuntimeException("이미 가입된 유저입니다");
        }

        User user = userRequestDto.toUser(passwordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }
    @Override
    @Transactional
    public TokenResponseDto login(UserRequestDto userRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = userRequestDto.toAuthentication();
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때
        //    CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication
                = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateToken(authentication);

        //Refresh Token 저장
        return tokenResponseDto;
    }

    @Override
    public void logout(HttpServletResponse response) {
    }

    @Transactional
    @Override
    public User changeRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));

        user.setRole(UserRole.ROLE_ADMIN);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString())); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        System.out.println("SecurityContextHolder.getContext() = " + SecurityContextHolder.getContext());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("유효하지 않은 Refresh Token");
        }

        // Access Token 에서 User id 가져오기
        Authentication authentication
                = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        //User id를 기반으로 Refresh Token 가져오기
        User user = userRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new BadRequestException("존재하지 않는 유저"));

        //redis memory에 리프레시 토큰이 존재하는지 체크
        jwtTokenProvider.validateRefreshToken(user.getId().toString(), tokenRequestDto.getRefreshToken());

        //새로운 엑세스 토큰 생성
        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(authentication))
                .refreshToken(tokenRequestDto.getRefreshToken())
                .build();

        //토큰 발급
        return tokenResponseDto;
    }
}
