package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.UserJoinDto;
import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.jwt.Token;
import Backend.HIFI.auth.jwt.UserAuthentication;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import Backend.HIFI.user.UserRole;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public String getAccessToken(Token.Request token) {
        UserAuthentication userAuthentication = new UserAuthentication(
                token.getEmail(),
                token.getPassword()
        );
        return jwtTokenProvider.generateToken(userAuthentication);
    }

    @Override
    public String getDecodedToken(String token) {
        return jwtTokenProvider.getUserEmailFromJWT(token);
    }

    @Override
    public Long join(UserJoinDto userJoinDto) {
        return userRepository.save(User.builder()
                .email(userJoinDto.getEmail())
                .password(passwordEncoder.encode(userJoinDto.getPassword())) //비밀번호 hash 저장
                .name(userJoinDto.getName())
                .annonymous(false) //기본 공개여부는 true
                .role(UserRole.USER) //기본 권한은 USER
                .build()).getId();
    }
    @Override
    public String login(String email, String rawPassword, HttpServletResponse response) {
        final User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다"));

        //패스워드 일치하는지 검사
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다");
        }

        //엑세스토큰 생성
        String accessToken = jwtTokenProvider.generateToken(
                new UserAuthentication(email, user.getPassword()));

        System.out.println("accessToken = " + accessToken);

        //클라이언트 쿠키에 토큰 저장
        response.setHeader("X-AUTH-TOKEN", accessToken);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", accessToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return accessToken;
    }

    @Override
    public void logout(HttpServletResponse response) {
        //클라이언트 쿠키 강제 만료
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Transactional
    @Override
    public void changeRole(Long userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));

        user.setRole(UserRole.ADMIN);

        userRepository.save(user);
    }
}
