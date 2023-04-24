package Backend.HIFI.domain.auth;

import Backend.HIFI.domain.auth.dto.TokenRequestDto;
import Backend.HIFI.domain.auth.dto.TokenResponseDto;
import Backend.HIFI.domain.auth.dto.UserRequestDto;
import Backend.HIFI.domain.auth.dto.UserResponseDto;
import Backend.HIFI.domain.auth.jwt.JwtDto;
import Backend.HIFI.domain.auth.jwt.JwtTokenProvider;
import Backend.HIFI.domain.auth.oauth.kakao.KakaoUserDto;
import Backend.HIFI.global.error.exception.BadRequestException;
import Backend.HIFI.global.error.ErrorCode;
import Backend.HIFI.global.error.exception.NotFoundException;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.repository.UserRepository;
import Backend.HIFI.domain.user.UserRole;
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
import java.util.Optional;

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
            throw new BadRequestException(ErrorCode.USER_ALREADY_EXIST);
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

        JwtDto jwtDto = new JwtDto(userRequestDto.toUser(passwordEncoder));

        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateToken(jwtDto);

        //Refresh Token 저장
        return tokenResponseDto;
    }

    @Override
    @Transactional
    public TokenResponseDto loginKakao(KakaoUserDto kakaoUserDto) {
        String provider = "kakao";

        log.info("로그인 시도");

        Optional<User> user = userRepository.findByEmailAndProvider(
                kakaoUserDto.getKakaoAccount().getEmail(),
                provider
        );

        if (user.isPresent()) {
            log.info("가입된 회원");
            /* 이미 가입된 회원 */
            TokenResponseDto tokenResponseDto = jwtTokenProvider.generateSocialToken(user.get());
            return tokenResponseDto;
        } else {
            /* 새로 가입할 회원 */

            String email = kakaoUserDto.getKakaoAccount().getEmail();
            String name = kakaoUserDto.getProperties().getNickname();

            User newUser = User.builder()
                    .email(email)
//                    .name(name)
                    .role(UserRole.ROLE_USER)
                    .provider(provider)
                    .authenticationCode(kakaoUserDto.getAuthenticationCode())
                    .build();
            userRepository.save(newUser);

            log.info("새로운 회원");

            TokenResponseDto tokenResponseDto = jwtTokenProvider.generateSocialToken(newUser);
            return tokenResponseDto;
        }
    }

    @Override
    public void logout(HttpServletResponse response) {
    }

    @Transactional
    @Override
    public User changeRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

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
            throw new BadRequestException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        // Access Token 에서 User id 가져오기
        Authentication authentication
                = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        //User id를 기반으로 Refresh Token 가져오기
        User user = userRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        //redis memory에 리프레시 토큰이 존재하는지 체크
        jwtTokenProvider.validateRefreshToken(user.getId().toString(), tokenRequestDto.getRefreshToken());

        //새로운 엑세스 토큰 생성
        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(new JwtDto(user)))
                .refreshToken(tokenRequestDto.getRefreshToken())
                .build();

        //토큰 발급
        return tokenResponseDto;
    }
}
